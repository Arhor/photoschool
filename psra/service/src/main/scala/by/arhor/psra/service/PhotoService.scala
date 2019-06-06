package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.PhotoDto
import by.arhor.psra.model.{Photo, User}
import org.modelmapper.ModelMapper

trait PhotoService extends Service {

  override type Model = Photo
  override type Dto   = PhotoDto
  override type Id    = String

  protected val modelMapper: ModelMapper

  def findPhotosByTag(tag: String, requester: User): util.List[PhotoDto]

  protected def mapToDTO(model: Model): Dto = modelMapper.map[Dto] (model, classOf[Dto])

  protected def mapToEntity(dto: Dto): Model = modelMapper.map[Model] (dto, classOf[Model])

}
