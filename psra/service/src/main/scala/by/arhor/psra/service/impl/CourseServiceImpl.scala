package by.arhor.psra.service.impl

import java.time.LocalDateTime
import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.CourseDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.ErrorLabel
import by.arhor.psra.repository.{CourseRepository, UserRepository}
import by.arhor.psra.service.CourseService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired

class CourseServiceImpl @Autowired() (
  private val repository: CourseRepository,
  private val userRepository: UserRepository,
  override protected val modelMapper: ModelMapper,
) extends CourseService {

  override def findOne(id: String): CourseDto =
    repository
      .findById(id)
      .map[CourseDto] { mapToDto }
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.COURSE_NOT_FOUND, "ID", id)
      }

  override def findAll(): util.List[CourseDto] =
    repository
      .findAll
      .stream
      .map[CourseDto] { mapToDto }
      .collect(toList())

  override def create(dto: CourseDto): CourseDto = {
    lazy val course = mapToEntity(dto)
    course.dateTimeCreated = LocalDateTime.now()
    lazy val created = repository.insert(course)
    mapToDto(created)
  }

  override def update(dto: CourseDto): CourseDto = {
    lazy val course = repository
      .findById(dto.id)
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.COURSE_NOT_FOUND, "ID", dto.id)
      }
    val toUpdate = mapToEntity(dto)

    toUpdate.dateTimeCreated = course.dateTimeCreated
    toUpdate.dateTimeUpdated = LocalDateTime.now()


    lazy val updated = repository.save(toUpdate)
    mapToDto(updated)
  }

  override def delete(dto: CourseDto): Unit =
    repository
      .findById(dto.id)
      .map[Unit] { repository.delete(_) }
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.COURSE_NOT_FOUND, "ID", dto.id)
      }

  override def addLearnersToCourse(courseId: String, userIds: Array[String]): CourseDto = {
    lazy val course =
      repository
        .findById(courseId)
        .orElseThrow {
          () => new EntityNotFoundException(ErrorLabel.COURSE_NOT_FOUND, "ID", courseId)
        }

    lazy val users = for {
      uid <- userIds
      user = userRepository
        .findById(uid)
        .orElseThrow {
          () => new EntityNotFoundException(ErrorLabel.USER_NOT_FOUND, "ID", uid)
        }
    } yield user

    users.foreach { course.learners.add(_) }

    val updated = repository.save(course)
    mapToDto(updated)
  }
}
