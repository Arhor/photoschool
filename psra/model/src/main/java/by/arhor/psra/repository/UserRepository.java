package by.arhor.psra.repository;

import java.util.Optional;

import by.arhor.psra.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository
	extends MongoRepository<User, String>
		    , BasicRepository<User, String> {

	@Query("{ 'enabled' : true, 'username' : '?0' }")
	Optional<User> findByUsername(String username);

}
