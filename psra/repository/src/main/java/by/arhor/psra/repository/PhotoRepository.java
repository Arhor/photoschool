package by.arhor.psra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import by.arhor.psra.repository.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
