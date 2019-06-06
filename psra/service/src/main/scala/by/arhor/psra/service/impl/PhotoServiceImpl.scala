package by.arhor.psra.service.impl

import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.PhotoDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.Error
import by.arhor.psra.model.{Photo, User}
import by.arhor.psra.repository.PhotoRepository
import by.arhor.psra.service.PhotoService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PhotoServiceImpl @Autowired() (

  private val repository: PhotoRepository,
	override protected val modelMapper: ModelMapper

) extends PhotoService {

	@Transactional(readOnly = true)
	override def findOne(id: String): PhotoDto = repository
		.findById(id)
  	.map[PhotoDto] { mapToDTO }
		.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", id) }

	@Transactional(readOnly = true)
	override def findAll(): util.List[PhotoDto] = repository
		.findAll()
		.stream()
		.map[PhotoDto] { mapToDTO }
  	.collect(toList())

	@Transactional(readOnly = true)
	override def findPhotosByTag(tag: String, requester: User): util.List[PhotoDto] = repository
		.findPhotosByTag(tag)
		.stream()
		.map[PhotoDto] { mapToDTO }
		.collect(toList())

	override def create(dto: PhotoDto): PhotoDto = {
		lazy val photo: Photo = mapToEntity(dto) // lazy?
		lazy val created: Photo = repository.insert(photo) // lazy?
		mapToDTO(created)
	}

	override def update(dto: PhotoDto): PhotoDto = repository
		.findById(dto.getId)
  	.map[Photo] { _ => mapToEntity(dto) }
		.map[Photo] { repository save _ }
		.map[PhotoDto] { mapToDTO _ }
		.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", dto.getId) }

	override def delete(dto: PhotoDto): Unit = repository
		.findById(dto.getId)
		.map[Unit] { repository delete _ } // TODO: does it work?
		.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", dto.getId) }

}
