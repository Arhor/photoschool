package by.arhor.psra.repository.model.enums

object Roles {

  sealed trait RolesEnum

  case object ROLE_GUEST   extends RolesEnum
  case object ROLE_USER    extends RolesEnum
  case object ROLE_MANAGER extends RolesEnum
  case object ROLE_ADMIN   extends RolesEnum

  val values: Seq[RolesEnum] = Seq(
    ROLE_GUEST,
    ROLE_USER,
    ROLE_MANAGER,
    ROLE_ADMIN
  )

}
