package by.arhor.psra.service.impl

import java.time.LocalDateTime
import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.ErrorLabel
import by.arhor.psra.repository.{CommentRepository, PhotoRepository}
import by.arhor.psra.service.CommentService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired

class CommentServiceImpl @Autowired() (
  private val repository: CommentRepository,
  private val photoRepository: PhotoRepository,
  override protected val modelMapper: ModelMapper,
) extends CommentService {

  override def findOne(id: String): CommentDto =
    repository
      .findById(id)
      .map[CommentDto] { mapToDto }
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.COMMENT_NOT_FOUND, "ID", id)
      }

  override def findAll(): util.List[CommentDto] =
    repository
      .findAll
      .stream
      .map[CommentDto] { mapToDto }
      .collect(toList())

  override def findCommentsByPhotoId(pid: String): util.List[CommentDto] =
    photoRepository
      .findById(pid)
      .map[util.List[CommentDto]] { photo =>
        photo
          .comments
          .stream
          .map[CommentDto] { mapToDto }
          .collect(toList())
      }
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.PHOTO_NOT_FOUND, "ID", pid)
      }

  override def create(dto: CommentDto): CommentDto = {
    lazy val comment = mapToEntity(dto)
    comment.dateTimeCreated = LocalDateTime.now()
    lazy val created = repository.insert(comment)
    mapToDto(created)
  }

  override def update(dto: CommentDto): CommentDto = {
    lazy val comment = repository
      .findById(dto.id)
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.COMMENT_NOT_FOUND, "ID", dto.id)
      }
    comment.content = dto.content
    comment.dateTimeUpdated = LocalDateTime.now()
    lazy val updated = repository.save(comment)
    mapToDto(updated)
  }

  override def delete(dto: CommentDto): Unit =
    repository
      .findById(dto.id)
      .map[Unit] { repository.delete(_) }
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.COMMENT_NOT_FOUND, "ID", dto.id)
      }
}
