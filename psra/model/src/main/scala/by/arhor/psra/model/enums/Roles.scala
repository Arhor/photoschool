package by.arhor.psra.model.enums

object Roles {

  sealed trait Role

  case object ROLE_GUEST   extends Role
  case object ROLE_USER    extends Role
  case object ROLE_MANAGER extends Role
  case object ROLE_ADMIN   extends Role

  final def values(): Array[Role] = Array(
    ROLE_GUEST,
    ROLE_USER,
    ROLE_MANAGER,
    ROLE_ADMIN
  )

}
