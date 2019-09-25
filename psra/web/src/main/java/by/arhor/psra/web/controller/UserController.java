package by.arhor.psra.web.controller;

import by.arhor.psra.dto.UserDto;
import by.arhor.psra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin // does it really necessary ?
@RestController
@RequestMapping(path = "/users")
public class UserController {

	private final UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service= service;
	}
	
	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('ROLE_ADMIN')")
	public UserDto getUserById(@PathVariable("id") String id) {
		return service.findOne(id);
	}

	@GetMapping(path = "/", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('ROLE_ADMIN')")
	public List<UserDto> getAllUsers() {
		return service.findAll();
	}

	@PostMapping(path = "/", produces = APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("!(#oauth2.hasScope('ROLE_USER') and #oauth2.hasScope('ROLE_ADMIN'))")
	public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {
		final var registered = service.create(dto);

		final var headers = new HttpHeaders();
		headers.setLocation(
				ControllerLinkBuilder
						.linkTo(UserController.class)
						.slash(registered.getId())
						.toUri());

		return new ResponseEntity<>(registered, headers, HttpStatus.CREATED);
	}

}
