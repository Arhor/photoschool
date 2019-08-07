package by.arhor.psra.service.impl

import java.util
import java.util.Collections
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.UserDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.ErrorLabel
import by.arhor.psra.model.User
import by.arhor.psra.repository.{CourseRepository, UserRepository}
import by.arhor.psra.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService, UsernameNotFoundException}
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Primary
@Service
@Transactional
class UserServiceImpl @Autowired() (
  private val repository: UserRepository,
	private val courseRepository: CourseRepository,
	private val encoder: PasswordEncoder,
	override protected val modelMapper: ModelMapper
) extends UserService
     with UserDetailsService {

	type SpringUser = org.springframework.security.core.userdetails.User

	@throws(classOf[UsernameNotFoundException])
	@Transactional(readOnly = true)
	override def loadUserByUsername(username: String): UserDetails =
		repository
			.findByUsername(username)
  		.map[UserDetails] { user =>
				new SpringUser(
					user.username,
					user.password,
					Collections.singletonList(new SimpleGrantedAuthority(user.role.toString))
				)
			}
			.orElseThrow { () => new UsernameNotFoundException(username) }

	@Transactional(readOnly = true)
	override def findOne(id: String): UserDto =
		repository
			.findById(id)
			.map[UserDto] { mapToDto }
  		.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.USER_NOT_FOUND, "ID", id)
			}
	
	@Transactional(readOnly = true)
	override def findAll(): util.List[UserDto] =
		repository
			.findAll
			.stream
			.map[UserDto] { mapToDto }
			.collect(toList())

	override def findLearnersByCourseId(cid: String): util.List[UserDto] =
		courseRepository
			.findById(cid)
			.map[util.List[UserDto]] { course =>
				course
					.learners
					.stream
					.map[UserDto] { mapToDto }
					.collect(toList())
			}
			.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.COURSE_NOT_FOUND, "ID", cid)
			}

	override def create(dto: UserDto): UserDto = {
		// FIXME Check for name duplicates

		lazy val user: User = mapToEntity(dto) // lazy?
		user.password = encoder.encode(user.password)
		lazy val created: User = repository.insert(user) // lazy?
		mapToDto(created)
	}

	override def update(dto: UserDto): UserDto = ??? // TODO Auto-generated method stub

	override def delete(dto: UserDto): Unit =
		repository
			.findById(dto.getId)
			.map[Unit] { repository delete _ } // TODO: does it work?
			.orElseThrow {
				() => new EntityNotFoundException(ErrorLabel.USER_NOT_FOUND, "ID", dto.getId)
			}
}
