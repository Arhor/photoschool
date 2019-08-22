package by.arhor.psra.model

import java.beans.BeanProperty

import by.arhor.psra.model.traits.{Auditable, Identifiable, Request}

abstract class RegistrationRequest
  extends Request
     with Identifiable[String]
     with Auditable {

  @BeanProperty
  var user: User = _

}
