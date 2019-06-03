package by.arhor.psra.model

import by.arhor.psra.CoreVersion
import by.arhor.psra.model.traits.Identifiable
import org.springframework.data.mongodb.core.mapping.{DBRef, Document}

import scala.beans.BeanProperty

object Comment {
  val serialVersionUID: Long = CoreVersion.SERIAL_VERSION_UID
}

@Document("comments")
class Comment extends Entity {

  @BeanProperty
  var content: String = _
  
  @DBRef/*(`lazy` = true)*/
  @BeanProperty
  var user: User = _

  override def toString: String = s"${getClass.getSimpleName} [" +
    s"id=$id, " +
    s"enabled=$enabled, " +
    s"dateTimeCreated=$dateTimeCreated, " +
    s"dateTimeUpdated=$dateTimeUpdated, " +
    s"content=$content, " +
    s"user=$user]"

}
