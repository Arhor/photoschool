package by.arhor.psra.mapper;

public interface Mapper<E, D> {

	D mapToDto(E entity);
	
	E mapToEntity(D dto);
	
}
