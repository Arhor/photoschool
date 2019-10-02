package by.arhor.psra.service.reactive.impl;

import by.arhor.psra.dto.Dto;
import by.arhor.psra.model.Entity;
import by.arhor.psra.repository.reactive.BaseRepository;
import by.arhor.psra.service.reactive.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractService<E extends Entity, D extends Dto, K> implements Service<D, K> {

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

  @Override
  @Transactional(readOnly = true)
  public Mono<D> findOne(K id) {
    return repository
        .findById(id)
        .map(this::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Flux<D> findAll() {
    return repository
        .findAll()
        .map(this::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Flux<D> findAll(int page, int size) {
    return repository
        .findAll()
        .skip(size * page)
        .take(size)
        .map(this::toDto);
  }

  @Override
  @Transactional
  public void delete(K id) {
    repository
        .findById(id)
        .map(c -> {
          c.setEnabled(false);
          return c;
        })
        .subscribe(repository::save);
  }

  D toDto(E entity) {
    return mapper.map(entity, dtoClass);
  }

}
