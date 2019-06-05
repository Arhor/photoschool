package by.arhor.psra.service

import by.arhor.psra.dto.CourseDTO

trait CourseService extends Service {

  type DTO = CourseDTO
  type ID  = String

}
