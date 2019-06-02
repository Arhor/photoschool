package by.arhor.psra.model.enums

object Roles extends Enumeration {

  type Roles = Value

  val ROLE_GUEST   = Value("ROLE_GUEST")
  val ROLE_USER    = Value("ROLE_USER")
  val ROLE_MANAGER = Value("ROLE_MANAGER")
  val ROLE_ADMIN   = Value("ROLE_ADMIN")

}
