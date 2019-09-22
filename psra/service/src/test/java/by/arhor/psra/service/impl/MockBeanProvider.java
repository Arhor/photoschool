package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.dto.CourseDto;
import by.arhor.psra.dto.GalleryDto;
import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.dto.UserDto;
import by.arhor.psra.model.Comment;
import by.arhor.psra.model.Course;
import by.arhor.psra.model.Gallery;
import by.arhor.psra.model.Photo;
import by.arhor.psra.model.User;
import by.arhor.psra.repository.CommentRepository;
import by.arhor.psra.repository.CourseRepository;
import by.arhor.psra.repository.GalleryRepository;
import by.arhor.psra.repository.PhotoRepository;
import by.arhor.psra.repository.UserRepository;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class MockBeanProvider {

  @MockBean protected ModelMapper modelMapper;

  @MockBean protected CommentRepository commentRepository;
  @MockBean protected PhotoRepository photoRepository;
  @MockBean protected UserRepository userRepository;
  @MockBean protected CourseRepository courseRepository;
  @MockBean protected GalleryRepository galleryRepository;

  @Mock protected Comment comment;
  @Mock protected Photo photo;
  @Mock protected User user;
  @Mock protected Course course;
  @Mock protected Gallery gallery;

  @Mock protected CommentDto commentDto;
  @Mock protected PhotoDto photoDto;
  @Mock protected UserDto userDto;
  @Mock protected CourseDto courseDto;
  @Mock protected GalleryDto galleryDto;

  protected <T> List<T> listOf(int size, Supplier<T> generator) {
    return Stream.generate(generator).limit(size).collect(toList());
  }

}
