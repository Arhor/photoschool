package by.arhor.psra.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import by.arhor.psra.dto.UserDto;

public interface UserService extends Service<UserDto, String>, UserDetailsService {

}
