package by.arhor.psra.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.repository.PhotoRepository;
import by.arhor.psra.service.PhotoService;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

	private PhotoRepository photoRepository;
    private Assembler<Photo, PhotoDto> assembler
	
	@Autowired
	public PhotoServiceImpl(PhotoRepository photoRepository
                            Assembler<Photo, PhotoDto> assembler) {
		this.photoRepository = photoRepository;
        this.assembler = assembler;
	}
	
	@Override
    @Transactional(readOnly = true)
	public PhotoDto findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<PhotoDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhotoDto create(PhotoDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhotoDto update(PhotoDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PhotoDto dto) {
		// TODO Auto-generated method stub
	}

}
