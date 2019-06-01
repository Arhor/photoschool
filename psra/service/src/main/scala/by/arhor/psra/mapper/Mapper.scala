package by.arhor.psra.mapper

trait Mapper[E, D] {

	def mapToDto(entity: E): D
	
	def mapToEntity(dto: D): E
	
}
