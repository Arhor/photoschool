package by.arhor.psra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public UserDto getUserById(@PathVariable("id") String id) {
		return service.findOne(id);
	}

}
