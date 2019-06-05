package by.arhor.psra.web.controller

import by.arhor.psra.dto.PhotoDTO
import by.arhor.psra.service.PhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/photos"))
class PhotoController(@Autowired private val service: PhotoService) {
	
	@GetMapping(path = Array("/{id}"), produces = Array("application/json"))
	def getPhotoById(@PathVariable("id") id: String): PhotoDTO = service.findOne(id)
	
}
