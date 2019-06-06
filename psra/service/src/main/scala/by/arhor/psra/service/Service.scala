package by.arhor.psra.service

import java.util

import org.modelmapper.ModelMapper

/**
 * 
 * @author Maksim Buryshynets
 * @version 1.0, 02 Jun 2019
 */
trait Service {

  type Model
  type Dto
  type Id

  /**
   * Method serves to fetch an entity by it's ID.
   *
   * @param  id enitity's identifier
   * @return entity object instance
   */
  def findOne(id: Id): Dto

  def findAll(): util.List[Dto]

  def create(dto: Dto): Dto

  def update(dto: Dto): Dto

  def delete(dto: Dto): Unit

}
