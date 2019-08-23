package by.arhor.psra.model

import java.util.{List => JavaList}

import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

@Document("galleries")
class Gallery extends Entity {

  @BeanProperty
  var name: String = _

  @BeanProperty
  var visibility: Visibilities = _

  @DBRef(`lazy` = true)
  @BeanProperty
  var  photos: JavaList[Photo] = _

  override def equals(other: Any): Boolean = other match {
    case that: Gallery =>
      that.isInstanceOf[Gallery] &&
      super.equals(that) &&
      that.name == this.name &&
      that.visibility == this.visibility
    case _ =>
      false
  }

  override def hashCode(): Int = (super.hashCode, name, visibility).##

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"name=$name, " +
    s"visibility=$visibility" +
    s"]"
}
