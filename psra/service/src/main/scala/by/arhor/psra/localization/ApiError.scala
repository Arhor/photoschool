package by.arhor.psra.localization

import scala.beans.BeanProperty

object ApiError {

  sealed abstract class ErrorLabel(@BeanProperty val value: String) extends Label

  case object COMMENT_NOT_FOUND extends ErrorLabel("error.not.found.comment")
  case object COURSE_NOT_FOUND  extends ErrorLabel("error.not.found.course")
  case object GALLERY_NOT_FOUND extends ErrorLabel("error.not.found.gallery")
  case object PHOTO_NOT_FOUND   extends ErrorLabel("error.not.found.photo")
  case object USER_NOT_FOUND    extends ErrorLabel("error.not.found.user")

  def values: Seq[Label] = Seq(
    COMMENT_NOT_FOUND,
    COURSE_NOT_FOUND,
    GALLERY_NOT_FOUND,
    PHOTO_NOT_FOUND,
    USER_NOT_FOUND
  )

}
