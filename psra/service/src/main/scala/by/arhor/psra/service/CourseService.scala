package by.arhor.psra.service

import by.arhor.psra.dto.CourseDto
import by.arhor.psra.model.Course
import org.modelmapper.ModelMapper

trait CourseService extends Service {

  override type Model = Course
  override type Dto   = CourseDto
  override type Id    = String

  protected val modelMapper: ModelMapper

  protected def mapToDTO(model: Model): Dto = modelMapper.map[Dto] (model, classOf[Dto])

  protected def mapToEntity(dto: Dto): Model = modelMapper.map[Model] (dto, classOf[Model])

}
