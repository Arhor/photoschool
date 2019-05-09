package by.arhor.psra.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.label.Label;
import by.arhor.psra.mapper.Mapper;
import by.arhor.psra.repository.PhotoRepository;
import by.arhor.psra.repository.model.Photo;
import by.arhor.psra.service.PhotoService;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

	private PhotoRepository photoRepository;
    private Mapper<Photo, PhotoDto> mapper;
	
	@Autowired
	public PhotoServiceImpl(PhotoRepository photoRepository,
                            Mapper<Photo, PhotoDto> mapper) {
		this.photoRepository = photoRepository;
        this.mapper = mapper;
	}
	
	@Override
    @Transactional(readOnly = true)
	public PhotoDto findOne(String id) {		
		return photoRepository
				.findById(id)
				.map(mapper::mapToDto)
				.orElseThrow(() -> new EntityNotFoundException(
						Label.ERROR_PHOTO_NOT_FOUND,
						"ID",
						id
				));
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<PhotoDto> findAll() {
		return photoRepository
				.findAll()
				.stream()
				.map(mapper::mapToDto)
				.collect(toList());
	}

	@Override
	public PhotoDto create(PhotoDto dto) {
		Photo photo = mapper.mapToEntity(dto);
		photo = photoRepository.insert(photo);
		return mapper.mapToDto(photo);
	}

	@Override
	public PhotoDto update(PhotoDto dto) {
		Photo photo = mapper.mapToEntity(dto);
		photo = photoRepository.save(photo);
		return mapper.mapToDto(photo);
	}

	@Override
	public void delete(PhotoDto dto) {
		Photo photo = photoRepository
				.findById(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException(
						Label.ERROR_PHOTO_NOT_FOUND,
						"ID",
						dto.getId()
				));
		photoRepository.delete(photo);
	}

}
