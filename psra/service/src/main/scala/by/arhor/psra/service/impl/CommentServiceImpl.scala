package by.arhor.psra.service.impl

import java.time.LocalDateTime
import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.ErrorLabel
import by.arhor.psra.model.Comment
import by.arhor.psra.repository.{CommentRepository, PhotoRepository}
import by.arhor.psra.service.CommentService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentServiceImpl @Autowired() (
  private val repository: CommentRepository,
  private val photoRepository: PhotoRepository,
  override protected val modelMapper: ModelMapper,
) extends CommentService {

  @Transactional(readOnly = true)
  override def findOne(id: String): CommentDto =
    repository
      .findByIdAndEnabledTrue(id)
      .map[CommentDto] { _.as[CommentDto] }
      .orElseThrow(() => new EntityNotFoundException(ErrorLabel.COMMENT_NOT_FOUND, "ID", id))

  @Transactional(readOnly = true)
  override def findAll(): util.List[CommentDto] =
    repository
      .findAll
      .stream
      .map[CommentDto] { _.as[CommentDto] }
      .collect(toList())

  override def create(dto: CommentDto): CommentDto = {
    val comment = dto.as[Comment]
    comment.dateTimeCreated = LocalDateTime.now()
    val created = repository.insert(comment)
    created.as[CommentDto]
  }

  override def update(dto: CommentDto): CommentDto = {
    val comment = repository
      .findByIdAndEnabledTrue(dto.id)
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.COMMENT_NOT_FOUND, "ID", dto.id)
      }
    comment.content = dto.content
    comment.dateTimeUpdated = LocalDateTime.now()
    val updated = repository.save(comment)
    updated.as[CommentDto]
  }

  override def delete(dto: CommentDto): Unit = {
    val comment = repository
      .findByIdAndEnabledTrue(dto.id)
      .orElseThrow(() => new EntityNotFoundException(ErrorLabel.COMMENT_NOT_FOUND, "ID", dto.id))
    comment.enabled = false
    repository save comment
  }
}
