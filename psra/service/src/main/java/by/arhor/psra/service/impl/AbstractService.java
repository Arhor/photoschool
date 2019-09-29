package by.arhor.psra.service.impl;

import by.arhor.psra.dto.Dto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.ErrorLabel;
import by.arhor.psra.model.Entity;
import by.arhor.psra.repository.BaseRepository;
import by.arhor.psra.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractService<E extends Entity, D extends Dto, K>
    implements Service<D, K> {

  protected final BaseRepository<E, K> repository;
  protected final ModelMapper mapper;
  protected final Class<D> dtoClass;

  public AbstractService(BaseRepository<E, K> repository,
                         ModelMapper mapper,
                         Class<D> dtoClass) {
    this.repository = repository;
    this.mapper = mapper;
    this.dtoClass = dtoClass;
  }

  protected abstract ErrorLabel notFoundLabel();

  @Override
  @Transactional(readOnly = true)
  public D findOne(K id) {
    return repository
        .findById(id)
        .map(this::toDto)
        .orElseThrow(() -> new EntityNotFoundException(notFoundLabel(), "ID", id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<D> findAll() {
    return repository
        .findAll()
        .stream()
        .map(this::toDto)
        .collect(toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<D> findAll(int page, int size) {
    return repository
        .findAll(PageRequest.of(page, size))
        .stream()
        .map(this::toDto)
        .collect(toList());
  }

  @Override
  public void delete(K id) {
    final var entity = repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(notFoundLabel(), "ID", id));

    entity.setEnabled(false);
    repository.save(entity);
  }

  D toDto(E entity) {
    return mapper.map(entity, dtoClass);
  }
}
