package by.arhor.psra.web.controller;

import by.arhor.psra.dto.CourseDto;
import by.arhor.psra.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping(path = "/courses")
public class CourseController {

  private final CourseService service;

  @Autowired
  public CourseController(CourseService service) {
    this.service = service;
  }

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
  public CourseDto getGalleryById(@PathVariable("id") String id) {
    return service.findOne(id);
  }
}
