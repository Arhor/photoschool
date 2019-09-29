package by.arhor.psra.repository;

import java.util.Optional;

import by.arhor.psra.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends BaseRepository<User, String> {

	@Query("{ 'enabled' : true, 'username' : '?0' }")
	Optional<User> findByUsername(String username);

	@Query(value = "{ 'username' : '?0' }", exists = true)
	boolean existsByUsername(String username);

	@Query("{ 'enabled' : true, 'email' : '?0' }")
	Optional<User> findByEmail(String email);

	@Query(value = "{ 'email' : '?0' }", exists = true)
	boolean existsByEmail(String email);

}
