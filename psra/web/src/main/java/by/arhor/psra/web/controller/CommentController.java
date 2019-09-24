package by.arhor.psra.web.controller;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping(path = "/comments")
public class CommentController {

  private final CommentService service;

  @Autowired
  public CommentController(CommentService service) {
    this.service = service;
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
  @PreAuthorize("#oauth2.hasScope('ROLE_USER') or #oauth2.hasScope('ROLE_ADMIN')")
  public CommentDto getCommentById(@PathVariable("id") String id) {
    return service.findOne(id);
  }
}
