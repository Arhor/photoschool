package by.arhor.psra.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingDelegate {

  private final ModelMapper modelMapper;

  @Autowired
  public MappingDelegate(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public <T, R> R map(T item, Class<R> targetClass) {
    return modelMapper.map(item, targetClass);
  }

}
