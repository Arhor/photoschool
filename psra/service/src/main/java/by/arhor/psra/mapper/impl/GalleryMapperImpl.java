package by.arhor.psra.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.arhor.psra.dto.GalleryDto;
import by.arhor.psra.repository.model.Gallery;

@Component
public class GalleryMapperImpl extends AbstractMapper<Gallery, GalleryDto> {

	@Autowired
	public GalleryMapperImpl(ModelMapper mapper) {
		super(mapper);
	}

	@Override
	public GalleryDto mapToDto(Gallery entity) {
		return mapper.map(entity, GalleryDto.class);
	}

	@Override
	public Gallery mapToEntity(GalleryDto dto) {
		return mapper.map(dto, Gallery.class);
	}

}
