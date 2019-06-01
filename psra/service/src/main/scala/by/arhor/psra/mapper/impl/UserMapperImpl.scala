package by.arhor.psra.mapper.impl

import by.arhor.psra.dto.UserDto
import by.arhor.psra.mapper.Mapper
import by.arhor.psra.repository.model.User
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserMapperImpl @Autowired() (private val mapper: ModelMapper) extends Mapper[User, UserDto] {

	override def mapToDto(entity: User): UserDto = mapper.map(entity, classOf[UserDto])

	override def mapToEntity(dto: UserDto): User = mapper.map(dto, classOf[User])

}
