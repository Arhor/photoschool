package by.arhor.psra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import by.arhor.psra.repository.model.Tag;

public interface TagRepository extends MongoRepository<Tag, String> {

}
