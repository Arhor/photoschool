package by.arhor.psra.repository

import by.arhor.psra.model.Course
import org.springframework.data.mongodb.repository.MongoRepository

trait CourseRepository
  extends MongoRepository[Course, String]
    with BasicRepository[Course, String]
