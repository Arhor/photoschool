package by.arhor.psra.service.impl

import java.time.LocalDateTime
import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.{CommentDto, PhotoDto, UserDto}
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.ErrorLabel
import by.arhor.psra.model.{Comment, Photo}
import by.arhor.psra.repository.{CommentRepository, PhotoRepository, UserRepository}
import by.arhor.psra.service.PhotoService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PhotoServiceImpl @Autowired() (
  private val repository: PhotoRepository,
	private val commentRepository: CommentRepository,
	private val userRepository: UserRepository,
	override protected val modelMapper: ModelMapper
) extends PhotoService {

	@Transactional(readOnly = true)
	override def findOne(id: String): PhotoDto =
		repository
			.findById(id)
  		.map[PhotoDto] { mapToDto }
			.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.PHOTO_NOT_FOUND, "ID", id)
			}

	@Transactional(readOnly = true)
	override def findAll(): util.List[PhotoDto] =
		repository
			.findAll
			.stream
			.map[PhotoDto] { mapToDto }
  		.collect(toList())

	@Transactional(readOnly = true)
	override def findPhotosByTag(tag: String, requester: UserDto): util.List[PhotoDto] =
		repository
			.findPhotosByTag(tag)
			.stream
			.map[PhotoDto] { mapToDto }
			.collect(toList())

	override def findCommentsByPhotoId(pid: String): util.List[CommentDto] =
		repository
			.findById(pid)
			.map[util.List[CommentDto]] { photo =>
				photo
					.comments
					.stream
					.map[CommentDto] { modelMapper.map(_, classOf[CommentDto]) }
					.collect(toList())
			}
			.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.PHOTO_NOT_FOUND, "ID", pid)
			}

	override def addCommentToPhoto(photoId: String, username: String, dto: CommentDto): Unit = {
		val photo = repository
			.findById(photoId)
			.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.PHOTO_NOT_FOUND, "ID", photoId)
			}
		val user = userRepository
			.findByUsername(username)
  		.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.USER_NOT_FOUND, "Username", username)
			}
		val comment = modelMapper.map(dto, classOf[Comment])
		comment.user = user
		comment.dateTimeCreated = LocalDateTime.now
		comment.dateTimeUpdated = LocalDateTime.now
		val createdComment = commentRepository.insert(comment)
		photo.comments.add(createdComment)
		repository.save(photo)
	}

	override def create(dto: PhotoDto): PhotoDto = {
		val photo: Photo = mapToEntity(dto)
		photo.dateTimeCreated = LocalDateTime.now
		photo.dateTimeUpdated = LocalDateTime.now
		val created: Photo = repository.insert(photo)
		mapToDto(created)
	}

	override def update(dto: PhotoDto): PhotoDto = ???
//		repository
//			.findById(dto.getId)
//  		.map[Photo] { _ => mapToEntity(dto) }
//			.map[Photo] { repository save _ }
//			.map[PhotoDto] { mapToDto _ }
//			.orElseThrow { () => new EntityNotFoundException(ErrorLabel.PHOTO_NOT_FOUND, "ID", dto.getId) }

	override def delete(dto: PhotoDto): Unit =
		repository
			.findById(dto.getId)
			.map[Unit] { repository delete _ } // TODO: does it work?
			.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.PHOTO_NOT_FOUND, "ID", dto.getId)
			}
}
