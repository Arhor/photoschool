package by.arhor.psra.web.controller

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.service.GalleryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/galleries"))
class GalleryController(@Autowired service: GalleryService) {
	
	@GetMapping(path = Array("/{id}"), produces = Array("application/json"))
	def getGalleryById(@PathVariable("id") id: String): GalleryDto = service.findOne(id)

}
