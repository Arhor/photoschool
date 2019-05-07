package by.arhor.psra.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.mapper.Mapper;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.repository.model.User;
import by.arhor.psra.service.UserService;

@Service
@Primary
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
    private Mapper<User, UserDto> mapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository,
                           Mapper<User, UserDto> mapper) {
		this.userRepository = userRepository;
        this.mapper = mapper;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto create(UserDto dto) {
		// TODO Auto-generated method stub
		return null;
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
