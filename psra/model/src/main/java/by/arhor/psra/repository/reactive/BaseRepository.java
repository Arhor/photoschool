package by.arhor.psra.repository.reactive;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends ReactiveMongoRepository<T, ID> {

  @Override
  @Query("{ enabled : true, _id : ?0 }")
  Mono<T> findById(ID id);

  @Override
  @Query("{ enabled : true }")
  Flux<T> findAll();

  @Override
  @Query("{ enabled : true }")
  Flux<T> findAll(Sort sort);

  @Override
  <S extends T> Mono<S> insert(S entity);

  @Override
  <S extends T> Flux<S> insert(Iterable<S> entities);

  @Override
  <S extends T> Mono<S> save(S entity);

  @Override
  <S extends T> Flux<S> saveAll(Iterable<S> entities);

}
