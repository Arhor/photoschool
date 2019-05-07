package by.arhor.psra.mapper.impl;

import org.modelmapper.ModelMapper;

import by.arhor.psra.mapper.Mapper;

public abstract class AbstractMapper<E, D> implements Mapper<E, D> {

	protected ModelMapper mapper;
	
	public AbstractMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
}
