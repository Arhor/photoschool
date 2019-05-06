package by.arhor.psra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import by.arhor.psra.repository.model.Gallery;

public interface GalleryRepository extends MongoRepository<Gallery, String> {

}
