package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CourseDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.ErrorLabel;
import by.arhor.psra.model.Course;
import by.arhor.psra.repository.CourseRepository;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static by.arhor.psra.localization.ErrorLabel.COURSE_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

  private final CourseRepository repository;
  private final UserRepository userRepository;
  private final ModelMapper mapper;

  @Autowired
  public CourseServiceImpl(
      CourseRepository repository,
      UserRepository userRepository,
      ModelMapper mapper) {

    this.repository = repository;
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  @Transactional(readOnly = true)
  public CourseDto findOne(String id) {
    return repository
        .findByIdAndEnabledTrue(id)
        .map(course -> mapper.map(course, CourseDto.class))
        .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CourseDto> findAll() {
    return repository
        .findAll()
        .stream()
        .map(course -> mapper.map(course, CourseDto.class))
        .collect(toList());
  }

  @Override
  public CourseDto create(CourseDto dto) {
    final var course = mapper.map(dto, Course.class);
    final var created = repository.insert(course);
    return mapper.map(created, CourseDto.class);
  }

  @Override
  public CourseDto update(CourseDto dto) {
    final var course = repository
      .findByIdAndEnabledTrue(dto.getId())
      .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", dto.getId()));

    course.setName(dto.getName());
    course.setDescription(dto.getDescription());

    final var updated = repository.save(course);
    return mapper.map(updated, CourseDto.class);
  }

  @Override
  public void delete(CourseDto dto) {
    final var course = repository
      .findByIdAndEnabledTrue(dto.getId())
      .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", dto.getId()));

    course.setEnabled(false);

    repository.save(course);
  }

  @Override
  public CourseDto addLearnersToCourse(String courseId, String[] userIds) {
    final var course = repository
        .findByIdAndEnabledTrue(courseId)
        .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", courseId));

    if (course.getLearners() == null) {
      course.setLearners(new ArrayList<>());
    }

    for (String uid : userIds) {
      final var user = userRepository
          .findByIdAndEnabledTrue(uid)
          .orElseThrow(() -> new EntityNotFoundException(ErrorLabel.USER_NOT_FOUND, "ID", uid));
      course.getLearners().add(user);
    }

    final var updated = repository.save(course);
    return mapper.map(updated, CourseDto.class);
  }
}
