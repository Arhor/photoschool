package by.arhor.psra.repository.model.enums

object Visibilities {

  sealed trait VisibilitiesEnum

  case object PUBLIC    extends VisibilitiesEnum
  case object PROTECTED extends VisibilitiesEnum
  case object PRIVATE   extends VisibilitiesEnum

  val values: Seq[VisibilitiesEnum] = Seq(
    PUBLIC,
    PROTECTED,
    PRIVATE
  )
}
