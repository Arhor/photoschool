package by.arhor.psra.web

package object controller {

  implicit def stringToArray(s: String): Array[String] = Array(s)
  implicit def throwableToArray[T <: Throwable](t: T): Array[T] = Array(t)

}
