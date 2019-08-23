package by.arhor.psra.model

import java.util.{List => JavaList}
import java.util.Objects

import by.arhor.psra.CoreVersion
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

@Document("users")
class User extends Entity {

  @Indexed(unique = true)
  @BeanProperty
  var username: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var role: Role = _

  @BeanProperty
  @DBRef(`lazy` = true)
  var galleries: JavaList[Gallery] = _

  override def equals(other: Any): Boolean = other match {
    case that: User =>
      that.isInstanceOf[User] &&
      super.equals(that) &&
      that.username == this.username &&
      that.password == this.password &&
      that.role == this.role
    case _ =>
      false
  }

  override def hashCode(): Int = (super.hashCode, username, password, role).##

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled" +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"username=$username, " +
    s"password=$password, " +
    s"role=$role" +
    s"]"
}
