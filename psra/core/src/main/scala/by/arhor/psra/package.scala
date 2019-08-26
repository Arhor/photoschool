package by.arhor

package object psra {

  /* type negation */
  type ¬[A] = A => Nothing

  type V[T, U] = ¬[¬[T] with ¬[U]]

  type ¬¬[A] = ¬[¬[A]]

  /* union of types */
  type |[T, U] = {
    type λ[X] = ¬¬[X] <:< (T V U)
  }

}
