package by.arhor.psra.repository;

import by.arhor.psra.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository
  extends MongoRepository<Course, String>
        , BasicRepository<Course, String> {

}
