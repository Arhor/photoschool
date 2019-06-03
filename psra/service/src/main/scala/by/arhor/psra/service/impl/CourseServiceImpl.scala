package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.model.Course
import by.arhor.psra.repository.CourseRepository
import by.arhor.psra.service.CourseService
import org.springframework.beans.factory.annotation.Autowired

class CourseServiceImpl @Autowired() (private val repository: CourseRepository) extends CourseService {

  override def findOne(id: String): Course = ???

  override def findAll(): util.List[Course] = ???

  override def create(dto: Course): Course = ???

  override def update(dto: Course): Course = ???

  override def delete(dto: Course): Unit = ???

}
