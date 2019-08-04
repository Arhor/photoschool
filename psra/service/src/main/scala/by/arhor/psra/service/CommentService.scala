package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.model.Comment

trait CommentService extends Service {

  override type M = Comment
  override type D = CommentDto
  override type K = String

  def findCommentsByPhotoId(pid: K): util.List[D]

}
