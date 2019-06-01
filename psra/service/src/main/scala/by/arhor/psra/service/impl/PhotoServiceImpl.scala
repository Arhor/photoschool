package by.arhor.psra.service.impl

import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.PhotoDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.Error
import by.arhor.psra.mapper.Mapper
import by.arhor.psra.repository.PhotoRepository
import by.arhor.psra.repository.model.Photo
import by.arhor.psra.service.PhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class PhotoServiceImpl @Autowired() (private val repository: PhotoRepository,
																		 private val mapper: Mapper[Photo, PhotoDto]) extends PhotoService {

	@Transactional(readOnly = true)
	override def findOne(id: String): PhotoDto = repository.findById(id)
		.map[PhotoDto] { mapper mapToDto _ }
		.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", id) }

	@Transactional(readOnly = true)
	override def findAll(): util.List[PhotoDto] = repository.findAll()
  	.stream()
		.map[PhotoDto] { mapper mapToDto _ }
		.collect(toList())

	@Transactional
	override def create(dto: PhotoDto): PhotoDto = mapper.mapToDto(repository.insert(mapper mapToEntity dto))

	@Transactional
	override def update(dto: PhotoDto): PhotoDto = repository.findById(dto.getId)
		.map[Photo] { _ => mapper mapToEntity dto }
		.map[Photo] { repository save _ }
		.map[PhotoDto] { mapper mapToDto _ }
		.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", dto.getId) }

	@Transactional
	override def delete(dto: PhotoDto): Unit = repository delete
		repository
			.findById(dto.getId)
			.orElseThrow { () => new EntityNotFoundException(Error.PHOTO_NOT_FOUND, "ID", dto.getId) }

}
