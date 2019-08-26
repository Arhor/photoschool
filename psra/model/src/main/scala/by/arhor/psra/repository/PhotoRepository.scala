package by.arhor.psra.repository

import java.util

import by.arhor.psra.model.Photo
import org.springframework.data.mongodb.repository.{MongoRepository, Query}

trait PhotoRepository
  extends MongoRepository[Photo, String]
    with BasicRepository[Photo, String] {

  @Query("{ 'enabled' : true, 'tags' : { $in : ?0 } }")
  def findByAnyOfTags(tags: Array[String]): util.List[Photo]

  @Query("{ 'enabled' : true, 'tags' : { $all : ?0 } }")
  def findByAllOfTags(tags: Array[String]): util.List[Photo]

}
