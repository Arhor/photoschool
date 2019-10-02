package by.arhor.psra.service.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Service<D, K> {

  Mono<D> findOne(K id);

  Flux<D> findAll();

  Flux<D> findAll(int page, int size);

  Mono<D> create(D dto);

  Mono<D> update(D dto);

  void delete(K id);

}
