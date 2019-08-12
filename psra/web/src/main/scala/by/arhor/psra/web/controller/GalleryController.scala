package by.arhor.psra.web.controller

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.service.GalleryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, PathVariable, RequestMapping, RestController}

@CrossOrigin
@RestController
@RequestMapping(path = "/galleries")
class GalleryController(@Autowired service: GalleryService) {
	
	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
	def getGalleryById(@PathVariable("id") id: String): GalleryDto = service.findOne(id)

}
