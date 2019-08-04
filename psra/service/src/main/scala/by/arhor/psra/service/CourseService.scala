package by.arhor.psra.service

import by.arhor.psra.dto.CourseDto
import by.arhor.psra.model.Course

trait CourseService extends Service {

  override type M = Course
  override type D = CourseDto
  override type K = String

}
