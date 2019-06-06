package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.dto.CourseDto
import by.arhor.psra.repository.CourseRepository
import by.arhor.psra.service.CourseService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired

class CourseServiceImpl @Autowired() (

  private val repository: CourseRepository,
  override protected val modelMapper: ModelMapper

) extends CourseService {

  override def findOne(id: String): CourseDto = ???

  override def findAll(): util.List[CourseDto] = ???

  override def create(dto: CourseDto): CourseDto = ???

  override def update(dto: CourseDto): CourseDto = ???

  override def delete(dto: CourseDto): Unit = ???

}
