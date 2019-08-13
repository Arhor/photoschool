package by.arhor.psra.repository

import java.util

import by.arhor.psra.model.Photo
import org.springframework.data.mongodb.repository.{MongoRepository, Query}

trait PhotoRepository extends MongoRepository[Photo, String] {

  @Query("{ 'tags' : { $in : [?0] } }")
  def findByTag(tag: String): util.List[Photo]

  @Query("{ 'tags' : { $in : ?0 } }")
  def findByAnyOfTags(tags: Array[String]): util.List[Photo]

  @Query("{ 'tags' : { $all : ?0 } }")
  def findByAllOfTags(tags: Array[String]): util.List[Photo]

}
