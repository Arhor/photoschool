package by.arhor.psra.service;

import by.arhor.psra.dto.Dto;

import java.util.List;

public interface Service<D extends Dto, K> {

  D findOne(K id);
  List<D> findAll();
  D create(D dto);
  D update(D dto);
  void delete(D dto);

//  implicit protected def convert[T, R](from: T, to: Class[R]): R = modelMapper.map[R](from, to)
//
//  implicit protected class DtoToEntity[T <: Dto](dto: T) {
//    def as[R <: Entity](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
//      val targetClass = tag.runtimeClass.asInstanceOf[Class[R]]
//      f(dto, targetClass)
//    }
//  }
//
//  implicit protected class EntityToDto[T <: Entity](entity: T) {
//    def as[R <: Dto](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
//      val targetClass = tag.runtimeClass.asInstanceOf[Class[R]]
//      f(entity, targetClass)
//    }
//  }
}
