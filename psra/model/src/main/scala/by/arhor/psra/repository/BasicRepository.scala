package by.arhor.psra.repository

import java.util.Optional

import org.springframework.data.domain.{Page, Pageable}

trait BasicRepository[T, K] {

  def findByIdAndEnabledTrue(id: K): Optional[T]

  def findAllByEnabledTrue(pageable: Pageable): Page[T]

}
