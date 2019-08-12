package by.arhor.psra.model

import java.util.Objects

import by.arhor.psra.CoreVersion
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Comment {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("comments")
class Comment extends Entity {

  @BeanProperty
  var content: String = _
  
  @DBRef
  @BeanProperty
  var user: User = _

  override def equals(obj: Any): Boolean = {
    if (super.equals(obj) && getClass == obj.getClass) {
      val comment = obj.asInstanceOf[Comment]
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

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"content=$content, " +
    s"user=$user]"

}
