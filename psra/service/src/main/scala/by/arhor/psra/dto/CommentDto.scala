package by.arhor.psra.dto

import java.util.Objects

import scala.beans.BeanProperty

class CommentDto extends Dto {

  @BeanProperty var content: String = _
  @BeanProperty var user: UserDto = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val comment = obj.asInstanceOf[CommentDto]
      content == comment.content &&
      user == comment.user
    } else {
      false
    }
  }

  override def hashCode(): Int = super.hashCode() + Objects.hash(
    getContent,
    getUser
  )
}
