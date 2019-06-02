package by.arhor.psra.repository.model.traits

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

import scala.beans.BeanProperty

trait Identifiable extends Comparable[Identifiable] {

  @Id
  @BeanProperty
  var id: ObjectId = _

  override def compareTo(that: Identifiable): Int = this.id compareTo that.id

}
