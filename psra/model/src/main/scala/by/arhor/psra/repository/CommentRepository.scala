package by.arhor.psra.repository

import by.arhor.psra.model.Comment
import org.springframework.data.mongodb.repository.MongoRepository

trait CommentRepository extends MongoRepository[Comment, String]
