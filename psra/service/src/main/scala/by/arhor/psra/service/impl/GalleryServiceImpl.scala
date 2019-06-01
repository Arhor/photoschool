package by.arhor.psra.service.impl

import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.Error
import by.arhor.psra.mapper.Mapper
import by.arhor.psra.repository.GalleryRepository
import by.arhor.psra.repository.model.Gallery
import by.arhor.psra.service.GalleryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GalleryServiceImpl @Autowired() (private val repository: GalleryRepository,
																			 private val mapper: Mapper[Gallery, GalleryDto]) extends GalleryService {

	@Transactional(readOnly = true)
	override def findOne(id: String): GalleryDto = repository.findById(id)
		.map[GalleryDto] { mapper mapToDto _ }
		.orElseThrow { () => new EntityNotFoundException(Error.GALLERY_NOT_FOUND, "ID", id) }


	@Transactional(readOnly = true)
	override def findAll(): util.List[GalleryDto] = repository.findAll()
  	.stream()
  	.map[GalleryDto] { mapper.mapToDto(_) }
  	.collect(toList())

	@Transactional
	override def create(dto: GalleryDto): GalleryDto = mapper mapToDto (repository insert (mapper mapToEntity dto))

	@Transactional
	override def update(dto: GalleryDto): GalleryDto = repository.findById(dto.getId)
		.map[Gallery] { _ => mapper mapToEntity dto }
		.map[Gallery] { repository save _ }
		.map[GalleryDto] { mapper mapToDto _ }
		.orElseThrow { () => new EntityNotFoundException(Error.GALLERY_NOT_FOUND, "ID", dto.getId) }


	@Transactional
	override def delete(dto: GalleryDto): Unit = repository delete
		repository
			.findById(dto.getId)
			.orElseThrow { () => new EntityNotFoundException(Error.GALLERY_NOT_FOUND, "ID", dto.getId) }

}
