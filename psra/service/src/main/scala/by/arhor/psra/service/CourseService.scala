package by.arhor.psra.service

import by.arhor.psra.dto.CourseDto
import by.arhor.psra.model.Course

trait CourseService extends Service[Course, CourseDto, String] {

  def addLearnersToCourse(courseId: String, learnersIds: Array[String]): CourseDto

}
