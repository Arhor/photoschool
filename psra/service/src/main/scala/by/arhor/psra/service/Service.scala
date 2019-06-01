package by.arhor.psra.service

import java.util

trait Service {
	
	type DTO
	type ID

	def findOne(id: ID): DTO
	
	def findAll(): util.List[DTO]
	
	def create(dto: DTO): DTO

	def update(dto: DTO): DTO
	
	def delete(dto: DTO): Unit
	
}
