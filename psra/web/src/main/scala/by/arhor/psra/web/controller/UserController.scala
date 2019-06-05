package by.arhor.psra.web.controller

import by.arhor.psra.dto.UserDTO
import by.arhor.psra.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/users"))
class UserController(@Autowired private val service: UserService) {
	
	@GetMapping(path = Array("/{id}"), produces = Array("application/json"))
	def getUserById(@PathVariable("id") id: String): UserDTO = service.findOne(id)

}
