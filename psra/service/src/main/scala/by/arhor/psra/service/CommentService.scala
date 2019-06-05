package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.CommentDTO

trait CommentService extends Service {

  type DTO = CommentDTO
  type ID  = String

  def findCommentsByPhotoId(pid: ID): util.List[CommentDTO]

}
