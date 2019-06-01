package by.arhor.psra.mapper.impl

import by.arhor.psra.dto.PhotoDto
import by.arhor.psra.mapper.Mapper
import by.arhor.psra.repository.model.Photo
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PhotoMapperImpl @Autowired() (private val mapper: ModelMapper) extends Mapper[Photo, PhotoDto] {

	override def mapToDto(entity: Photo): PhotoDto = mapper.map(entity, classOf[PhotoDto])

	override def mapToEntity(dto: PhotoDto): Photo = mapper.map(dto, classOf[Photo])

}
