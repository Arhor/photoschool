package by.arhor.psra.model

import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

@Document("comments")
class Comment extends Entity {

  @BeanProperty
  var content: String = _
  
  @DBRef
  @BeanProperty
  var user: User = _

  override def equals(other: Any): Boolean = other match {
    case that: Comment =>
      that.isInstanceOf[Comment] &&
      super.equals(that) &&
      that.content == this.content &&
      that.user == this.user
    case _ =>
      false
  }

  override def hashCode(): Int = (super.hashCode, content, user).##

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"content=$content, " +
    s"user=$user" +
    s"]"

}
