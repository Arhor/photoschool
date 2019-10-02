package by.arhor.psra.service.impl;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.exception.EntityDuplicateException;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.ErrorLabel;
import by.arhor.psra.model.User;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.service.UserService;
import by.arhor.psra.util.MappingDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Predicate;

import static by.arhor.psra.localization.ErrorLabel.USER_DUPLICATE;
import static by.arhor.psra.localization.ErrorLabel.USER_NOT_FOUND;
import static java.util.Collections.singletonList;

@Primary
@Service
@Transactional
public class UserServiceImpl
    extends AbstractService<User, UserDto, String>
    implements UserService
             , UserDetailsService {

  private final PasswordEncoder encoder;

  @Autowired
  public UserServiceImpl(UserRepository repository,
                         PasswordEncoder encoder,
                         MappingDelegate mapper) {
    super(repository, mapper, UserDto.class);
    this.encoder = encoder;
  }

  @Override
  protected ErrorLabel notFoundLabel() {
    return USER_NOT_FOUND;
  }

  @Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return ((UserRepository) repository)
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
    checkUsername(dto.getUsername());
    checkEmail(dto.getEmail());

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

  private void checkUsername(String username) {
    checkForDuplicates(((UserRepository) repository)::existsByUsername, username, "Username");
  }

  private void checkEmail(String email) {
    checkForDuplicates(((UserRepository) repository)::existsByEmail, email, "Email");
  }

  private <T> void checkForDuplicates(Predicate<T> checker, T value, String fieldName) {
    final var isTaken = checker.test(value);
    if (isTaken) {
      throw new EntityDuplicateException(USER_DUPLICATE, fieldName, value);
    }
  }
}
