package by.arhor.psra.repository;

import by.arhor.psra.model.Gallery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GalleryRepository
  extends MongoRepository<Gallery, String>
        , BasicRepository<Gallery, String> {

}
