package by.arhor.psra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import by.arhor.psra.repository.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
}
