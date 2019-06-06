package by.arhor.psra.web.controller

import by.arhor.psra.dto.CourseDto
import by.arhor.psra.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/courses"))
class CourseController(@Autowired service: CourseService) {

  @GetMapping(path = Array("/{id}"), produces = Array("application/json"))
  def getGalleryById(@PathVariable("id") id: String): CourseDto = service.findOne(id)

}
