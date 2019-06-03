package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.model.Comment
import by.arhor.psra.repository.CommentRepository
import by.arhor.psra.service.CommentService
import org.springframework.beans.factory.annotation.Autowired

class CommentServiceImpl @Autowired() (private val repository: CommentRepository) extends CommentService {

  override def findCommentsByPhotoId(pid: String): List[Comment] = ???

  override def findOne(id: String): Comment = ???

  override def findAll(): util.List[Comment] = ???

  override def create(dto: Comment): Comment = ???

  override def update(dto: Comment): Comment = ???

  override def delete(dto: Comment): Unit = ???

}
