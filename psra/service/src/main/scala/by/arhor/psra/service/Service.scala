package by.arhor.psra.service

import java.util

/**
 * 
 * @author Maksim Buryshynets
 * @version 1.0, 02 Jun 2019
 */
trait Service {

  type DTO
  type ID

  /**
   * Method serves to fetch an entity by it's ID.
   *
   * @param  id enitity's identifier
   * @return entity object instance
   */
  def findOne(id: ID): DTO

  def findAll(): util.List[DTO]

  def create(dto: DTO): DTO

  def update(dto: DTO): DTO

  def delete(dto: DTO): Unit

}
