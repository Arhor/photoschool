package by.arhor.psra.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.arhor.psra.dto.TagDto;
import by.arhor.psra.repository.TagRepository;
import by.arhor.psra.service.TagService;

@Service
@Transactional
public class TagServiceImpl implements TagService {
	
	private TagRepository tagRepository;
    private Assembler<Tag, TagDto> assembler;
	
	@Autowired
	public TagServiceImpl(TagRepository tagRepository
                          Assembler<Tag, TagDto> assembler) {
		this.tagRepository = tagRepository;
        this.assembler = assembler;
	}

	@Override
	@Transactional(readOnly = true)
	public TagDto findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<TagDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagDto create(TagDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagDto update(TagDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TagDto dto) {
		// TODO Auto-generated method stub
	}

}
