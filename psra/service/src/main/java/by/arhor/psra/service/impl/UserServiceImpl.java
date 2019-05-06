package by.arhor.psra.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
    private Assembler<User, UserDto> assembler;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository
                           Assembler<User, UserDto> assembler) {
		this.userRepository = userRepository;
        this.assembler = assembler;
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
