package by.arhor.psra.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.arhor.psra.dto.TagDto;
import by.arhor.psra.mapper.Mapper;
import by.arhor.psra.repository.TagRepository;
import by.arhor.psra.repository.model.Tag;
import by.arhor.psra.service.TagService;

@Service
@Transactional
public class TagServiceImpl implements TagService {
	
	private TagRepository tagRepository;
    private Mapper<Tag, TagDto> mapper;
	
	@Autowired
	public TagServiceImpl(TagRepository tagRepository,
                          Mapper<Tag, TagDto> mapper) {
		this.tagRepository = tagRepository;
        this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public TagDto findOne(String id) {
		return tagRepository.findOne(Example.of(new Tag(id)))
				.map(mapper::mapToDto)
				.orElseThrow(RuntimeException::new);
	}

	@Override
    @Transactional(readOnly = true)
	public Collection<TagDto> findAll() {
		return tagRepository.findAll()
				.stream()
				.map(mapper::mapToDto)
				.collect(toList());
	}

	@Override
	public TagDto create(TagDto dto) {
		// TODO Check for duplicates
		return Optional.ofNullable(dto)
				.map(mapper::mapToEntity)
				.map(tagRepository::insert)
				.map(mapper::mapToDto)
				.orElseThrow(RuntimeException::new);
	}

	@Override
	public TagDto update(TagDto dto) {
		// TODO Check for duplicates
		return Optional.ofNullable(dto)
				.map(mapper::mapToEntity)
				.map(tagRepository::save)
				.map(mapper::mapToDto)
				.orElseThrow(RuntimeException::new);
	}

	@Override
	public void delete(TagDto dto) {
		// TODO Check for presence
		tagRepository.delete(mapper.mapToEntity(dto));
	}

}
