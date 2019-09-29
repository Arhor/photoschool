package by.arhor.psra.service.impl;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.ErrorLabel;
import by.arhor.psra.model.User;
import by.arhor.psra.repository.CourseRepository;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.arhor.psra.localization.ErrorLabel.USER_NOT_FOUND;
import static java.util.Collections.singletonList;

@Primary
@Service
@Transactional
public class UserServiceImpl
    extends AbstractService<User, UserDto, String>
    implements UserService
             , UserDetailsService {

  private final UserRepository repository;
  private final CourseRepository courseRepository;
  private final PasswordEncoder encoder;

  @Autowired
  public UserServiceImpl(UserRepository repository,
                         CourseRepository courseRepository,
                         PasswordEncoder encoder,
                         ModelMapper mapper) {
    super(repository, mapper, UserDto.class);
    this.repository = repository;
    this.courseRepository = courseRepository;
    this.encoder = encoder;
  }

  @Override
  protected ErrorLabel notFoundLabel() {
    return USER_NOT_FOUND;
  }

  @Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository
        .findByUsername(username)
        .map(user ->
          new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            singletonList(new SimpleGrantedAuthority(user.getRole().toString()))))
			  .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  @Override
	public UserDto create(UserDto dto) {
		final var user = mapper.map(dto, User.class);

		user.setPassword(encoder.encode(user.getPassword()));

		final var created = repository.insert(user);
		return mapper.map(created, UserDto.class);
	}

	@Override
	public UserDto update(UserDto dto) {
    final var user = repository
        .findById(dto.getId())
        .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND, "ID", dto.getId()));

    user.setUsername(dto.getUsername());
    user.setPassword(encoder.encode(dto.getPassword()));

    final var updated = repository.save(user);

    return mapper.map(updated, UserDto.class);
  }
}
