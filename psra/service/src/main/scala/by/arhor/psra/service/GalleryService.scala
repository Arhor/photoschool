package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.model.Gallery

trait GalleryService extends Service {

  override type M = Gallery
  override type D = GalleryDto
  override type K = String

  def findGalleriesByUserId(uid: K): util.List[D]

}
