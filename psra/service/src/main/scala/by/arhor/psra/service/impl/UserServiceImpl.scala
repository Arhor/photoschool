package by.arhor.psra.service.impl

import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.UserDto
import by.arhor.psra.mapper.Mapper
import by.arhor.psra.repository.UserRepository
import by.arhor.psra.repository.model.User
import by.arhor.psra.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.{UserDetails, UsernameNotFoundException}
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

//import scala.collection.JavaConversions._

@Primary
@Service
class UserServiceImpl @Autowired() (private val repository: UserRepository,
																		private val mapper: Mapper[User, UserDto],
																		private val encoder: PasswordEncoder) extends UserService {

	@throws(classOf[UsernameNotFoundException])
	@Transactional(readOnly = true)
	override def loadUserByUsername(username: String): UserDetails = ???  // TODO Auto-generated method stub

	@Transactional(readOnly = true)
	override def findOne(id: String): UserDto = ??? // TODO Auto-generated method stub
	
	@Transactional(readOnly = true)
	override def findAll(): util.List[UserDto] = repository.findAll()
		.stream()
		.map[UserDto] { mapper mapToDto _ }
  	.collect(toList())

	@Transactional
	override def create(dto: UserDto): UserDto = {
		val newEntity = mapper mapToEntity dto
		// FIXME Check for name duplicates
		newEntity.setPassword(encoder.encode(newEntity.getPassword))
		mapper.mapToDto(repository insert newEntity)
	}

	@Transactional
	override def update(dto: UserDto): UserDto = ??? // TODO Auto-generated method stub

	@Transactional
	override def delete(dto: UserDto): Unit = ??? // TODO Auto-generated method stub

}
