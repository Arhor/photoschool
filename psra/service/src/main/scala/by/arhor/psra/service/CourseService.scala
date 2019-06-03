package by.arhor.psra.service

import by.arhor.psra.model.Course

trait CourseService extends Service {

  type Entity = Course
  type ID = String

}
