package by.arhor.psra.repository

import java.util

import by.arhor.psra.model.Photo
import org.springframework.data.mongodb.repository.{MongoRepository, Query}

trait PhotoRepository extends MongoRepository[Photo, String] {

  @Query("{ 'tags' : { $in : [?0] } }")
  def findPhotosByTag(tag: String): util.List[Photo]

}
