package by.arhor.psra.localization

import scala.beans.BeanProperty

object Error {

  sealed abstract class ErrorLabelsEnum(@BeanProperty val value: String) extends Label

  case object PHOTO_NOT_FOUND   extends ErrorLabelsEnum("error.not.found.photo")
  case object GALLERY_NOT_FOUND extends ErrorLabelsEnum("error.not.found.gallery")

  val values: Seq[Label] = Seq(
    PHOTO_NOT_FOUND,
    GALLERY_NOT_FOUND
  )

}
