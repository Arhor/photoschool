package by.arhor.psra.service

import by.arhor.psra.model.Gallery

trait GalleryService extends Service {

  override type Entity = Gallery
  override type ID = String

  def findGalleriesByUserId(uid: String): List[Gallery]

}
