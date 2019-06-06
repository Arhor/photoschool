package by.arhor.psra.web.controller

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/comments"))
class CommentController(@Autowired service: CommentService) {

  @GetMapping(path = Array("/{id}"), produces = Array("application/json"))
  def getGalleryById(@PathVariable("id") id: String): CommentDto = service.findOne(id)

}
