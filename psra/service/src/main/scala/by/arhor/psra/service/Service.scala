package by.arhor.psra.service

import java.util

trait Service {
	
	type Entity
	type ID

	def findOne(id: ID): Entity
	
	def findAll(): util.List[Entity]
	
	def create(dto: Entity): Entity

	def update(dto: Entity): Entity
	
	def delete(dto: Entity): Unit
	
}
