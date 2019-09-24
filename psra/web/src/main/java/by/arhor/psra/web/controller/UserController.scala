package by.arhor.psra.web.controller

import java.util

import by.arhor.psra.dto.UserDto
import by.arhor.psra.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, PathVariable, PostMapping, RequestBody, RequestMapping, RestController}

@CrossOrigin // does it really necessary ?
@RestController
@RequestMapping(path = Array("/users"))
class UserController(@Autowired private val service: UserService) {
	
	@GetMapping(path = Array("/{id}"), produces = Array(APPLICATION_JSON_UTF8_VALUE))
	@PreAuthorize("#oauth2.hasScope('ROLE_ADMIN')")
	def getUserById(@PathVariable("id") id: String): UserDto = service.findOne(id)

	@GetMapping(produces = Array(APPLICATION_JSON_UTF8_VALUE))
	@PreAuthorize("#oauth2.hasScope('ROLE_ADMIN')")
	def getAllUsers: util.List[UserDto] = service.findAll()

	@PostMapping(produces = Array(APPLICATION_JSON_UTF8_VALUE))
	@PreAuthorize("!(#oauth2.hasScope('ROLE_USER') and #oauth2.hasScope('ROLE_ADMIN'))")
	def register(@RequestBody dto: UserDto): ResponseEntity[UserDto] = {
		val created = service.create(dto)
		val headers = new HttpHeaders
		headers.setLocation(
			ControllerLinkBuilder
				.linkTo(classOf[UserController])
				.slash(created.getId)
				.toUri
		)
		new ResponseEntity[UserDto](created, headers, HttpStatus.CREATED)
	}

}
