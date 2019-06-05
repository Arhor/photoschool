package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.dto.CommentDTO
import by.arhor.psra.repository.CommentRepository
import by.arhor.psra.service.CommentService
import org.springframework.beans.factory.annotation.Autowired

class CommentServiceImpl @Autowired() (private val repository: CommentRepository) extends CommentService {

  override def findOne(id: String): CommentDTO = ???

  override def findAll(): util.List[CommentDTO] = ???

  override def findCommentsByPhotoId(pid: String): util.List[CommentDTO] = ???

  override def create(dto: CommentDTO): CommentDTO = ???

  override def update(dto: CommentDTO): CommentDTO = ???

  override def delete(dto: CommentDTO): Unit = ???

}
