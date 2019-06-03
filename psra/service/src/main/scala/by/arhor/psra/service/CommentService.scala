package by.arhor.psra.service

import by.arhor.psra.model.Comment

trait CommentService extends Service {

  type Entity = Comment
  type ID = String

  def findCommentsByPhotoId(pid: ID): List[Comment]

}
