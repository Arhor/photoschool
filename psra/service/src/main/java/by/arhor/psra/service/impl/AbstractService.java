//package by.arhor.psra.service.impl;
//
//import static java.util.stream.Collectors.toList;
//
//import java.util.Collection;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import by.arhor.psra.mapper.Mapper;
//import by.arhor.psra.repository.Repository;
//import by.arhor.psra.service.Service;
//
//public abstract class AbstractService<E, D, ID> implements Service<D, ID> {
//	
//	protected Repository<E, ID> repository;
//	protected Mapper<E, D> mapper;
//	
//	public AbstractService(Repository<E, ID> repository, Mapper<E, D> mapper) {
//		this.repository = repository;
//		this.mapper = mapper;
//	}
//
//	@Override
//    @Transactional(readOnly = true)
//	public Collection<D> findAll() {
//		return repository.findAll()
//				.stream()
//				.map(mapper::mapToDto)
//				.collect(toList());
//	}
//	
//	@Override
//	public D create(D dto) {
//		E newEntity = mapper.mapToEntity(dto);
//		newEntity = repository.insert(newEntity);
//		return mapper.mapToDto(newEntity);
//	}
//	
//}
