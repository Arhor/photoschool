package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.model.Gallery
import org.modelmapper.ModelMapper

trait GalleryService extends Service {

  override type Model = Gallery
  override type Dto   = GalleryDto
  override type Id    = String

  protected val modelMapper: ModelMapper

  def findGalleriesByUserId(uid: Id): util.List[GalleryDto]

  protected def mapToDTO(model: Model): Dto = modelMapper.map[Dto] (model, classOf[Dto])

  protected def mapToEntity(dto: Dto): Model = modelMapper.map[Model] (dto, classOf[Model])

}
