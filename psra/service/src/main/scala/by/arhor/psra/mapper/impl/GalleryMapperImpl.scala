package by.arhor.psra.mapper.impl

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.mapper.Mapper
import by.arhor.psra.repository.model.Gallery
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GalleryMapperImpl @Autowired() (private val mapper: ModelMapper) extends Mapper[Gallery, GalleryDto] {

	override def mapToDto(entity: Gallery): GalleryDto = mapper.map(entity, classOf[GalleryDto])

	override def mapToEntity(dto: GalleryDto): Gallery = mapper.map(dto, classOf[Gallery])

}
