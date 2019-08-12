package by.arhor.psra.web.controller

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, PathVariable, RequestMapping, RestController}

@CrossOrigin
@RestController
@RequestMapping(path = "/comments")
class CommentController(@Autowired service: CommentService) {

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
  @PreAuthorize("#oauth2.hasScope('ROLE_USER') or #oauth2.hasScope('ROLE_ADMIN')")
  def getCommentById(@PathVariable("id") id: String): CommentDto = service.findOne(id)

}
