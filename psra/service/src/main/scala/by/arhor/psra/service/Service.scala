package by.arhor.psra.service

import java.util

/**
 * 
 * @author Maksim Buryshynets
 * @version 1.0, 02 Jun 2019
 */
trait Service {

  type Entity
  type ID

  /**
   * Method serves to fetch an entity by it's ID.
   *
   * @param  id enitity's identifier
   * @return entity object instance
   */
  def findOne(id: ID): Entity

  def findAll(): util.List[Entity]

  def create(dto: Entity): Entity

  def update(dto: Entity): Entity

  def delete(dto: Entity): Unit

}
