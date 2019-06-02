package by.arhor.psra.localization

import scala.beans.BeanProperty

object Error {

  sealed abstract class ErrorLabels(@BeanProperty val value: String) extends Label

  case object GALLERY_NOT_FOUND extends ErrorLabels("error.not.found.gallery")
  case object PHOTO_NOT_FOUND   extends ErrorLabels("error.not.found.photo")
  case object USER_NOT_FOUND    extends ErrorLabels("error.not.found.user")

  val values: Seq[Label] = Seq(
    GALLERY_NOT_FOUND,
    PHOTO_NOT_FOUND,
    USER_NOT_FOUND
  )

}
