package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.CommentDto
import by.arhor.psra.model.Comment

trait CommentService extends Service[Comment, CommentDto, String] {

  def findCommentsByPhotoId(pid: String): util.List[CommentDto]

}
