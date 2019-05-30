package by.arhor.psra.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.Error;
import by.arhor.psra.mapper.Mapper;
import by.arhor.psra.repository.PhotoRepository;
import by.arhor.psra.repository.model.Photo;
import by.arhor.psra.service.PhotoService;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {
	
	private PhotoRepository repository;
	private Mapper<Photo, PhotoDto> mapper;
	
	@Autowired
	public PhotoServiceImpl(PhotoRepository repository, Mapper<Photo, PhotoDto> mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
    @Transactional(readOnly = true)
	public PhotoDto findOne(String id) {		
		return repository.findById(id)
				.map(mapper::mapToDto)
				.orElseThrow(() -> new EntityNotFoundException(
						Error.PHOTO_NOT_FOUND,
						"ID",
						id
				));
	}
	
	@Override
    @Transactional(readOnly = true)
	public Collection<PhotoDto> findAll() {
		return repository.findAll()
				.stream()
				.map(mapper::mapToDto)
				.collect(toList());
	}
	
	@Override
	public PhotoDto create(PhotoDto dto) {
		var newEntity = mapper.mapToEntity(dto);
		newEntity = repository.insert(newEntity);
		return mapper.mapToDto(newEntity);
	}

	@Override
	public PhotoDto update(PhotoDto dto) {
		Photo newPhoto = mapper.mapToEntity(dto);
		return repository.findById(dto.getId())
				.map(oldPhoto -> newPhoto)
				.map(repository::save)
				.map(mapper::mapToDto)
				.orElseThrow(() -> new EntityNotFoundException(
						Error.PHOTO_NOT_FOUND,
						"ID",
						dto.getId()
				));
	}

	@Override
	public void delete(PhotoDto dto) {
		Photo photo = repository.findById(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException(
						Error.PHOTO_NOT_FOUND,
						"ID",
						dto.getId()
				));
		repository.delete(photo);
	}

}
