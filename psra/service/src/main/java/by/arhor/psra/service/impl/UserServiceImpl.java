package by.arhor.psra.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.mapper.Mapper;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.repository.model.User;
import by.arhor.psra.service.UserService;

@Primary
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	private Mapper<User, UserDto> mapper;
	private PasswordEncoder encoder;
	
	@Autowired
	public UserServiceImpl(UserRepository repository,
						   Mapper<User, UserDto> mapper,
						   PasswordEncoder encoder) {
		this.repository = repository;
		this.mapper = mapper;
		this.encoder = encoder;
	}
	
	@Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public UserDto findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    @Transactional(readOnly = true)
	public Collection<UserDto> findAll() {
		return repository.findAll()
				.stream()
				.map(mapper::mapToDto)
				.collect(toList());
	}
	
	@Override
	public UserDto create(UserDto dto) {
		var newEntity = mapper.mapToEntity(dto);
		// FIXME Check for name duplicates
		newEntity.setPassword(encoder.encode(newEntity.getPassword()));
		newEntity = repository.insert(newEntity);
		return mapper.mapToDto(newEntity);
	}

	@Override
	public UserDto update(UserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UserDto dto) {
		// TODO Auto-generated method stub
	}

}
