package by.arhor.psra.model

import java.util.{List => JavaList, Set => JavaSet}

import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

@Document("photos")
class Photo extends Entity {
  
  @BeanProperty
  var name: String = _

  @BeanProperty
  var description: String = _

  @BeanProperty
  var path: String = _

  @BeanProperty
  var tags: JavaSet[String] = _

  @BeanProperty
  @DBRef(`lazy` = true)
  var comments: JavaList[Comment] = _

  override def equals(other: Any): Boolean = other match {
    case that: Photo =>
      that.isInstanceOf[Photo] &&
      super.equals(that) &&
      that.name == this.name &&
      that.description == this.description &&
      that.tags == this.tags
    case _ =>
      false
  }

  override def hashCode(): Int = (super.hashCode, name, description, tags).##

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"name=$name, " +
    s"description=$description, " +
    s"path=$path, " +
    s"tags=$tags" +
    s"]"
}
