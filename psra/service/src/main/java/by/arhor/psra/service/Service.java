package by.arhor.psra.service;

import java.util.Collection;

public interface Service<D, ID> {

	D findOne(ID id);
	
	Collection<D> findAll();
	
	D create(D dto);
		
	D update(D dto);
	
	void delete(D dto);
	
}
