package by.arhor.psra.repository;

import java.util.List;

import by.arhor.psra.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface PhotoRepository
  extends MongoRepository<Photo, String>
        , BasicRepository<Photo, String> {

  @Query("{ 'enabled' : true, 'tags' : { $in : ?0 } }")
  List<Photo> findByAnyOfTags(String[] tags);

  @Query("{ 'enabled' : true, 'tags' : { $all : ?0 } }")
  List<Photo> findByAllOfTags(String[] tags);

}
