package by.arhor.psra.service.impl;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.exception.EntityNotFoundException;
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

import java.util.List;

import static by.arhor.psra.localization.ErrorLabel.COURSE_NOT_FOUND;
import static by.arhor.psra.localization.ErrorLabel.USER_NOT_FOUND;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Primary
@Service
@Transactional
public class UserServiceImpl
    implements UserService
             , UserDetailsService {

  private final UserRepository repository;
  private final CourseRepository courseRepository;
  private final PasswordEncoder encoder;
  private final ModelMapper mapper;

  @Autowired
  public UserServiceImpl(
      UserRepository repository,
      CourseRepository courseRepository,
      PasswordEncoder encoder,
      ModelMapper mapper) {

    this.repository = repository;
    this.courseRepository = courseRepository;
    this.encoder = encoder;
    this.mapper = mapper;
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
	@Transactional(readOnly = true)
  public UserDto findOne(String id) {
    return repository
        .findByIdAndEnabledTrue(id)
        .map(user -> mapper.map(user, UserDto.class))
  		  .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND, "ID", id));
  }

  @Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {
    return repository
        .findAll()
        .stream()
        .map(user -> mapper.map(user, UserDto.class))
        .collect(toList());
  }

  @Override
	public List<UserDto> findLearnersByCourseId(String cid) {
    return courseRepository
        .findByIdAndEnabledTrue(cid)
        .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", cid))
        .getLearners()
        .stream()
        .map(user -> mapper.map(user, UserDto.class))
        .collect(toList());
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
        .findByIdAndEnabledTrue(dto.getId())
        .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND, "ID", dto.getId()));

    user.setUsername(dto.getUsername());
    user.setPassword(encoder.encode(dto.getPassword()));

    final var updated = repository.save(user);

    return mapper.map(updated, UserDto.class);
  }

  @Override
	public void delete(UserDto dto) {
    final var user = repository
        .findByIdAndEnabledTrue(dto.getId())
        .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND, "ID", dto.getId()));

    user.setEnabled(false);

    repository.save(user);
  }
}
