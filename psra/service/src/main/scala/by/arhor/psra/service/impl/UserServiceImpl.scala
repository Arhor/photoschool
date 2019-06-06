package by.arhor.psra.service.impl

import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.UserDto
import by.arhor.psra.model.User
import by.arhor.psra.repository.UserRepository
import by.arhor.psra.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService, UsernameNotFoundException}
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Primary
@Service
@Transactional
class UserServiceImpl @Autowired() (

  private val repository: UserRepository,
	private val encoder: PasswordEncoder,
	override protected val modelMapper: ModelMapper

) extends UserService
     with UserDetailsService{

	@throws(classOf[UsernameNotFoundException])
	@Transactional(readOnly = true)
	override def loadUserByUsername(username: String): UserDetails = ???  // TODO Auto-generated method stub

	@Transactional(readOnly = true)
	override def findOne(id: String): UserDto = ??? // TODO Auto-generated method stub
	
	@Transactional(readOnly = true)
	override def findAll(): util.List[UserDto] = repository
		.findAll()
		.stream()
		.map[UserDto] { mapToDTO }
		.collect(toList())

	override def create(dto: UserDto): UserDto = {
		// FIXME Check for name duplicates

		lazy val user: User = mapToEntity(dto) // lazy?
		user.setPassword(encoder.encode(user.getPassword))
		lazy val created: User = repository.insert(user) // lazy?
		mapToDTO(created)
	}

	override def update(dto: UserDto): UserDto = ??? // TODO Auto-generated method stub

	override def delete(dto: UserDto): Unit = ??? // TODO Auto-generated method stub

	override def findLearnersByCourseId(cid: String): util.List[UserDto] = ???

}
