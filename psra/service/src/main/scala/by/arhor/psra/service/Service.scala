package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.Dto
import by.arhor.psra.model.Entity
import org.modelmapper.ModelMapper

import scala.reflect.ClassTag

trait Service[M <: Entity, D <: Dto, K] {

  def findOne(id: K): D
  def findAll(): util.List[D]
  def create(dto: D): D
  def update(dto: D): D
  def delete(dto: D): Unit

  protected val modelMapper: ModelMapper

  def mapToDto(model: M)(implicit tag: ClassTag[D]): D = {
    modelMapper.map[D] (model, tag.runtimeClass.asInstanceOf[Class[D]])
  }

  def mapToEntity(dto: D)(implicit tag: ClassTag[M]): M = {
    modelMapper.map[M] (dto, tag.runtimeClass.asInstanceOf[Class[M]])
  }

}
