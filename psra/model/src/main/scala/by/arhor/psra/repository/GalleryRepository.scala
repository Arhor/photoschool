package by.arhor.psra.repository

import by.arhor.psra.model.Gallery
import org.springframework.data.mongodb.repository.MongoRepository

trait GalleryRepository
  extends MongoRepository[Gallery, String]
    with BasicRepository[Gallery, String]
