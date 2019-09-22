package by.arhor.psra.service;

import by.arhor.psra.dto.CourseDto;

public interface CourseService extends Service<CourseDto, String> {

  CourseDto addLearnersToCourse(String courseId, String[] learnersIds);

}
