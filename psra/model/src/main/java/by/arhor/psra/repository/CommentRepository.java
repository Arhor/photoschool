package by.arhor.psra.repository;

import by.arhor.psra.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository
  extends MongoRepository<Comment, String>
        , BasicRepository<Comment, String> {
  
}
