package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.UserDto
import by.arhor.psra.model.User
import org.modelmapper.ModelMapper

trait UserService extends Service {

  override type Model = User
  override type Dto   = UserDto
  override type Id    = String

  protected val modelMapper: ModelMapper

  def findLearnersByCourseId(cid: Id): util.List[UserDto]

  protected def mapToDTO(model: Model): Dto = modelMapper.map[Dto] (model, classOf[Dto])

  protected def mapToEntity(dto: Dto): Model = modelMapper.map[Model] (dto, classOf[Model])

}
