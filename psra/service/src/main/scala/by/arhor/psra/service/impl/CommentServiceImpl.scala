package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.repository.CommentRepository
import by.arhor.psra.service.CommentService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired

class CommentServiceImpl @Autowired() (

  private val repository: CommentRepository,
  override protected val modelMapper: ModelMapper

) extends CommentService {

  override def findOne(id: String): CommentDto = ???

  override def findAll(): util.List[CommentDto] = ???

  override def findCommentsByPhotoId(pid: String): util.List[CommentDto] = ???

  override def create(dto: CommentDto): CommentDto = ???

  override def update(dto: CommentDto): CommentDto = ???

  override def delete(dto: CommentDto): Unit = ???

}
