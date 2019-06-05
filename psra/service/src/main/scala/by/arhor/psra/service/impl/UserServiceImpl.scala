package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.model.User
import by.arhor.psra.repository.UserRepository
import by.arhor.psra.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.{UserDetails, UsernameNotFoundException}
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Primary
@Service
class UserServiceImpl @Autowired() (private val repository: UserRepository,
																		private val encoder: PasswordEncoder) extends UserService {

	@throws(classOf[UsernameNotFoundException])
	@Transactional(readOnly = true)
	override def loadUserByUsername(username: String): UserDetails = ???  // TODO Auto-generated method stub

	@Transactional(readOnly = true)
	override def findOne(id: String): User = ??? // TODO Auto-generated method stub
	
	@Transactional(readOnly = true)
	override def findAll(): util.List[User] = repository.findAll()

	@Transactional
	override def create(user: User): User = {
		// FIXME Check for name duplicates
		user.setPassword(encoder.encode(user.getPassword))
		repository.insert(user)
	}

	@Transactional
	override def update(user: User): User = ??? // TODO Auto-generated method stub

	@Transactional
	override def delete(user: User): Unit = ??? // TODO Auto-generated method stub
	override def findLearnersByCourseId(cid: String): util.List[User] = ???
}
