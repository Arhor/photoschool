package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.Dto
import by.arhor.psra.model.Entity
import org.modelmapper.ModelMapper

import scala.reflect.ClassTag

trait Service[D <: Dto, K] {

  def findOne(id: K): D
  def findAll(): util.List[D]
  def create(dto: D): D
  def update(dto: D): D
  def delete(dto: D): Unit

  protected val modelMapper: ModelMapper

  implicit protected def convert[T, R](from: T, to: Class[R]): R = modelMapper.map[R](from, to)

  implicit protected class DtoToEntity[T <: Dto](dto: T) {
    def as[R <: Entity](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
      val targetClass = tag.runtimeClass.asInstanceOf[Class[R]]
      f(dto, targetClass)
    }
  }

  implicit protected class EntityToDto[T <: Entity](entity: T) {
    def as[R <: Dto](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
      val targetClass = tag.runtimeClass.asInstanceOf[Class[R]]
      f(entity, targetClass)
    }
  }
//  def mapToDto(model: M)(implicit tag: ClassTag[D]): D = {
//    modelMapper.map[D] (model, tag.runtimeClass.asInstanceOf[Class[D]])
//  }
//
//  def mapToEntity(dto: D)(implicit tag: ClassTag[M]): M = {
//    modelMapper.map[M] (dto, tag.runtimeClass.asInstanceOf[Class[M]])
//  }
}
