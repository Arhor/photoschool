package by.arhor.psra.assembly.impl;

import org.modelmapper.ModelMapper;

import by.arhor.psra.assembly.Assembler;

public abstract class AbstractAssembler<From, To> implements Assembler<From, To> {

	protected ModelMapper mapper;
	
	public AbstractAssembler(ModelMapper mapper) {
		this.mapper = mapper;
	}
	
}
