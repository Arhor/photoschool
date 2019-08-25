package by.arhor.psra.dto

import by.arhor.psra.model.Entity
import org.modelmapper.ModelMapper

import scala.reflect.ClassTag

object Converters {

  private val mapper: ModelMapper = new ModelMapper

  implicit class DtoToEntity[T <: Dto](dto: T) {
    def as[R <: Entity](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
      val targetClass = tag.runtimeClass.asInstanceOf[Class[T]]
      f(dto, targetClass)
    }
  }

  implicit class EntityToDto[T <: Entity](entity: T) {
    def as[R <: Dto](implicit f: (T, Class[R]) => R, tag: ClassTag[R]): R = {
      val targetClass = tag.runtimeClass.asInstanceOf[Class[T]]
      f(entity, targetClass)
    }
  }

  implicit def convert[T, R](from: T, to: Class[R]): R = mapper.map[R](from, to)

}
