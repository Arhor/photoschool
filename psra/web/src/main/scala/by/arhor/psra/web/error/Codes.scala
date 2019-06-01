package by.arhor.psra.web.error

import scala.language.implicitConversions

object Codes extends Enumeration {

  sealed case class Code(value: String) extends super.Val {
    override def toString(): String = value
  }

  implicit def valueToCodeVal(x: Value): Code = x.asInstanceOf[Code]

  val NOT_FOUND = Code("10001")

}
