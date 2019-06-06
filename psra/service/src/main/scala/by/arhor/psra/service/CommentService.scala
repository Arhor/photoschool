package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.model.Comment
import org.modelmapper.ModelMapper

trait CommentService extends Service {

  override type Model = Comment
  override type Dto   = CommentDto
  override type Id    = String

  protected val modelMapper: ModelMapper

  def findCommentsByPhotoId(pid: Id): util.List[CommentDto]

  protected def mapToDTO(model: Model): Dto = modelMapper.map[Dto] (model, classOf[Dto])

  protected def mapToEntity(dto: Dto): Model = modelMapper.map[Model] (dto, classOf[Model])

}
