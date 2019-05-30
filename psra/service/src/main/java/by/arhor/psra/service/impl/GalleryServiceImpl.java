package by.arhor.psra.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.arhor.psra.dto.GalleryDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.Error;
import by.arhor.psra.mapper.Mapper;
import by.arhor.psra.repository.GalleryRepository;
import by.arhor.psra.repository.model.Gallery;
import by.arhor.psra.service.GalleryService;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {
	
	private GalleryRepository repository;
	private Mapper<Gallery, GalleryDto> mapper;
	
	@Autowired
	public GalleryServiceImpl(GalleryRepository repository, Mapper<Gallery, GalleryDto> mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Override
    @Transactional(readOnly = true)
	public GalleryDto findOne(String id) {
		return repository.findById(id)
				.map(mapper::mapToDto)
				.orElseThrow(() -> new EntityNotFoundException(
						Error.GALLERY_NOT_FOUND,
						"ID",
						id
				));
	}
	
	@Override
    @Transactional(readOnly = true)
	public Collection<GalleryDto> findAll() {
		return repository.findAll()
				.stream()
				.map(mapper::mapToDto)
				.collect(toList());
	}
	
	@Override
	public GalleryDto create(GalleryDto dto) {
		var newEntity = mapper.mapToEntity(dto);
		newEntity = repository.insert(newEntity);
		return mapper.mapToDto(newEntity);
	}

	@Override
	public GalleryDto update(GalleryDto dto) {
		Gallery newGallery = mapper.mapToEntity(dto);
		return repository.findById(dto.getId())
				.map(oldGallery -> newGallery)
				.map(repository::save)
				.map(mapper::mapToDto)
				.orElseThrow(() -> new EntityNotFoundException(
						Error.GALLERY_NOT_FOUND,
						"ID",
						dto.getId()
				));
	}

	@Override
	public void delete(GalleryDto dto) {
		Gallery gallery = repository.findById(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException(
						Error.GALLERY_NOT_FOUND,
						"ID",
						dto.getId()
				));
		repository.delete(gallery);
	}

}
