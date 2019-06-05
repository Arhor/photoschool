package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.GalleryDTO

trait GalleryService extends Service {

  override type DTO = GalleryDTO
  override type ID  = String

  def findGalleriesByUserId(uid: ID): util.List[GalleryDTO]

}
