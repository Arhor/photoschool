package by.arhor.psra.service;

import by.arhor.psra.dto.CourseDto;
import by.arhor.psra.dto.UserDto;

import java.util.List;

public interface CourseService extends Service<CourseDto, String> {

  CourseDto addLearnersToCourse(String courseId, String[] learnersIds);

  List<UserDto> findLearnersByCourseId(String cid);

}
