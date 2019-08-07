package by.arhor.psra.model.enums

object Visibilities {

  sealed trait Visibility

  case object PUBLIC    extends Visibility
  case object PROTECTED extends Visibility
  case object PRIVATE   extends Visibility

  final def values(): Array[Visibility] = Array(
    PUBLIC,
    PROTECTED,
    PRIVATE
  )

}
