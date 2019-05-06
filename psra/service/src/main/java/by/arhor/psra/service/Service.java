package by.arhor.psra.service;

import java.util.Collection;

public interface Service<T, K> {

	T findOne(K id);
	
	Collection<T> findAll();
	
	T create(T dto);
		
	T update(T dto);
	
	void delete(T dto);
	
}
