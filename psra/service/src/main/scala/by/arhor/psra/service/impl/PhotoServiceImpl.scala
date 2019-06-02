package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.Error
import by.arhor.psra.model.Photo
import by.arhor.psra.repository.PhotoRepository
import by.arhor.psra.service.PhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PhotoServiceImpl @Autowired() (private val repository: PhotoRepository) extends PhotoService {

	@Transactional(readOnly = true)
	override def findOne(id: String): Photo = repository
		.findById(id)
		.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", id) }

	@Transactional(readOnly = true)
	override def findAll(): util.List[Photo] = repository.findAll()

	@Transactional
	override def create(photo: Photo): Photo = repository.insert(photo)

	@Transactional
	override def update(photo: Photo): Photo = repository
		.findById(photo.getId)
		.map[Photo] { repository save _ }
		.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", photo.getId) }

	@Transactional
	override def delete(photo: Photo): Unit = repository delete
		repository
			.findById(photo.getId)
			.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", photo.getId) }

}
