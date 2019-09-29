package by.arhor.psra.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, ID> extends MongoRepository<T, ID> {

  @Override
  @Query("{ 'enabled' : true }")
  Optional<T> findById(ID id);

  @Override
  @Query("{ 'enabled' : true }")
  List<T> findAll();

  @Override
  @Query("{ 'enabled' : true }")
  List<T> findAll(Sort sort);

  @Override
  @Query("{ 'enabled' : true }")
  Page<T> findAll(Pageable pageable);

  @Override
  <S extends T> S insert(S entity);

  @Override
  <S extends T> List<S> insert(Iterable<S> entities);

  @Override
  <S extends T> S save(S entity);

  @Override
  <S extends T> List<S> saveAll(Iterable<S> entities);

}
