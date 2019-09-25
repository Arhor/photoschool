package by.arhor.psra.web.controller;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping(path = "/photos")
public class PhotoController {

  private final PhotoService service;

  @Autowired
  public PhotoController(PhotoService service) {
    this.service = service;
  }
	
	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
	public PhotoDto getPhotoById(@PathVariable("id") String id) {
    return service.findOne(id);
  }

  @GetMapping(path = "/{id}/comments", produces = APPLICATION_JSON_UTF8_VALUE)
  public List<CommentDto> getCommentsByPhotoId(@PathVariable("id") String id) {
    return service.findCommentsByPhotoId(id);
  }

  @PostMapping(path = "/{id}/comments", consumes = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity addCommentToPhoto(
      @PathVariable("id") String id,
      @RequestBody CommentDto dto,
      Authentication auth) {

    final var comment = service.addCommentToPhoto(id, auth.getName(), dto);

    final var headers = new HttpHeaders();
    headers.setLocation(
        ControllerLinkBuilder
            .linkTo(PhotoController.class)
            .slash(id)
            .slash("/comments")
            .slash(comment.getId())
            .toUri());

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }
}
