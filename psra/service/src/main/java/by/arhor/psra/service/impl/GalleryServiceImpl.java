package by.arhor.psra.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.arhor.psra.dto.GalleryDto;
import by.arhor.psra.repository.GalleryRepository;
import by.arhor.psra.service.GalleryService;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {

	private GalleryRepository galleryRepository;
    private Assembler<Gallery, GalleryDto> assembler;
	
	@Autowired
	public GalleryServiceImpl(GalleryRepository galleryRepository
                              Assembler<Gallery, GalleryDto> assembler) {
		this.galleryRepository = galleryRepository;
        this.assembler = assembler;
	}
	
	@Override
    @Transactional(readOnly = true)
	public GalleryDto findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<GalleryDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GalleryDto create(GalleryDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GalleryDto update(GalleryDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(GalleryDto dto) {
		// TODO Auto-generated method stub
	}

}
