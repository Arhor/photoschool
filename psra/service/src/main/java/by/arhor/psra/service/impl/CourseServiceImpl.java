package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CourseDto;
import by.arhor.psra.dto.UserDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.ErrorLabel;
import by.arhor.psra.model.Course;
import by.arhor.psra.model.User;
import by.arhor.psra.repository.CourseRepository;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.service.CourseService;
import by.arhor.psra.util.MappingDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static by.arhor.psra.localization.ErrorLabel.COURSE_NOT_FOUND;
import static by.arhor.psra.localization.ErrorLabel.USER_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class CourseServiceImpl
    extends AbstractService<Course, CourseDto, String>
    implements CourseService {

  private final UserRepository userRepository;

  @Autowired
  public CourseServiceImpl(CourseRepository repository,
                           UserRepository userRepository,
                           MappingDelegate mapper) {
    super(repository, mapper, CourseDto.class);
    this.userRepository = userRepository;
  }

  @Override
  protected ErrorLabel notFoundLabel() {
    return COURSE_NOT_FOUND;
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
      .findById(dto.getId())
      .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", dto.getId()));

    course.setName(dto.getName());
    course.setDescription(dto.getDescription());
    course.setLimit(dto.getLimit());

    final var updated = repository.save(course);
    return mapper.map(updated, CourseDto.class);
  }

  @Override
  public CourseDto addLearnersToCourse(String courseId, String[] userIds) {
    final var course = repository
        .findById(courseId)
        .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", courseId));

    var users = new ArrayList<User>(userIds.length);
    for (String uid : userIds) {
      final var user = userRepository
          .findById(uid)
          .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND, "ID", uid));
      users.add(user);
    }
    users.forEach(course::addLearner);

    final var updated = repository.save(course);
    return mapper.map(updated, CourseDto.class);
  }

  @Override
  public List<UserDto> findLearnersByCourseId(String cid) {
    return repository
        .findById(cid)
        .orElseThrow(() -> new EntityNotFoundException(COURSE_NOT_FOUND, "ID", cid))
        .getLearners()
        .stream()
        .map(user -> mapper.map(user, UserDto.class))
        .collect(toList());
  }
}
