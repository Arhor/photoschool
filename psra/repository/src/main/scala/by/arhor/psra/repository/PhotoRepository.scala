package by.arhor.psra.repository

import by.arhor.psra.model.Photo
import org.springframework.data.mongodb.repository.{MongoRepository, ReactiveMongoRepository}

trait PhotoRepository extends MongoRepository[Photo, String]
