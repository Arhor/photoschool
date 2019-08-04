package by.arhor.psra.model.traits

import org.springframework.data.annotation.Id

import scala.beans.BeanProperty

trait Identifiable extends Comparable[Identifiable] {

  @Id
  @BeanProperty
  var id: String = _

  override def compareTo(that: Identifiable): Int = this.id.compareTo(that.id)

}
