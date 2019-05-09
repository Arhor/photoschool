package by.arhor.psra.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.repository.model.Photo;

@Component
public class PhotoMapperImpl extends AbstractMapper<Photo, PhotoDto>{

	public PhotoMapperImpl(ModelMapper mapper) {
		super(mapper);
	}

	@Override
	public PhotoDto mapToDto(Photo entity) {
		return mapper.map(entity, PhotoDto.class);
	}

	@Override
	public Photo mapToEntity(PhotoDto dto) {
		return mapper.map(dto, Photo.class);
	}

}
