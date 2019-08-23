package by.arhor.psra.model

import java.util

import by.arhor.psra.CoreVersion
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

@Document("courses")
class Course extends Entity {

  @Indexed(unique = true)
  @BeanProperty
  var name: String = _
  
  @BeanProperty
  var description: String = _

  @DBRef(`lazy` = true)
  @BeanProperty
  var teacher: User = _

  @DBRef(`lazy` = true)
  @BeanProperty
  var learners: util.List[User] = _

  override def equals(other: Any): Boolean = other match {
    case that: Course =>
      that.isInstanceOf[Course] &&
      super.equals(that) &&
      that.name == this.name &&
      that.description == this.description
    case _ =>
      false
  }

  override def hashCode(): Int = (super.hashCode, name, description).##

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"name=$name, " +
    s"description=$description" +
    s"]"
}
