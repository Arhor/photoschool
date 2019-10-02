package by.arhor.psra.service;

import java.util.List;

public interface CanFind<T, K> {

  T findOne(K id);

  List<T> findAll();

  List<T> findAll(int page, int size);

}
