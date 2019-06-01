package by.arhor.psra.repository

import java.util.Optional

import by.arhor.psra.repository.model.User
import org.springframework.data.mongodb.repository.{MongoRepository, Query, ReactiveMongoRepository}

trait UserRepository extends MongoRepository[User, String] {

	@Query("{'username' : '?0'}")
	def findByUsername(username: String): Optional[User]

}
