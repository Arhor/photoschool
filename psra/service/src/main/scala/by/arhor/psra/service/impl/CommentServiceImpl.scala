package by.arhor.psra.service.impl

import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.ApiError
import by.arhor.psra.model.Comment
import by.arhor.psra.repository.{CommentRepository, PhotoRepository}
import by.arhor.psra.service.CommentService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired

class CommentServiceImpl @Autowired() (

  private val repository: CommentRepository,
  private val photoRepository: PhotoRepository,
  override protected val modelMapper: ModelMapper

) extends CommentService {

  override def findOne(id: String): CommentDto =
    repository
      .findById(id)
      .map[CommentDto] { mapToDto }
      .orElseThrow {
        () => new EntityNotFoundException(ApiError.COMMENT_NOT_FOUND, "ID", id)
      }

  override def findAll(): util.List[CommentDto] =
    repository
      .findAll
      .stream
      .map[CommentDto] { comment => mapToDto(comment) }
      .collect(toList())

  override def findCommentsByPhotoId(pid: String): util.List[CommentDto] =
    photoRepository
      .findById(pid)
      .map[util.List[CommentDto]] { photo =>
        photo
          .getComments
          .stream
          .map[CommentDto] { mapToDto }
          .collect(toList())
      }
      .orElseThrow {
        () => new EntityNotFoundException(ApiError.PHOTO_NOT_FOUND, "ID", pid)
      }

  override def create(dto: CommentDto): CommentDto =
    Some(dto)
      .map[Comment] { mapToEntity }
      .map[Comment] { repository insert _ }
      .map[CommentDto] { mapToDto }
      .get

  override def update(dto: CommentDto): CommentDto = ???

  override def delete(dto: CommentDto): Unit = ???

}
