package by.arhor.psra.web.controller

import java.security.Principal
import java.util

import by.arhor.psra.dto.{CommentDto, PhotoDto}
import by.arhor.psra.service.PhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, PathVariable, PostMapping, RequestBody, RequestMapping, RestController}

@CrossOrigin
@RestController
@RequestMapping(path = Array("/photos"))
class PhotoController(@Autowired private val service: PhotoService) {
	
	@GetMapping(path = Array("/{id}"), produces = Array(APPLICATION_JSON_UTF8_VALUE))
	def getPhotoById(@PathVariable("id") id: String): PhotoDto = service.findOne(id)

  @GetMapping(path = Array("/{id}/comments"), produces = Array(APPLICATION_JSON_UTF8_VALUE))
  def getCommentsByPhotoId(@PathVariable("id") id: String): util.List[CommentDto] = {
    service.findCommentsByPhotoId(id)
  }

  @PostMapping(path = Array("/{id}/comments"), consumes = Array(APPLICATION_JSON_UTF8_VALUE))
  def addCommentToPhoto(@PathVariable("id") id: String,
                        @RequestBody dto: CommentDto,
                        principal: Principal): ResponseEntity[Unit] = {
    val comment = service.addCommentToPhoto(id, principal.getName, dto)
    val headers = new HttpHeaders
    headers.setLocation(
      ControllerLinkBuilder
        .linkTo(classOf[PhotoController])
        .slash(id)
        .slash("/comments")
        .slash(comment.getId)
        .toUri
    )
    new ResponseEntity[Unit](headers, HttpStatus.CREATED)
  }

}
