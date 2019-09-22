package by.arhor.psra.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface BasicRepository<T, K> {

  Optional<T> findByIdAndEnabledTrue(K id);

  Page<T> findAllByEnabledTrue(Pageable pageable);

}
