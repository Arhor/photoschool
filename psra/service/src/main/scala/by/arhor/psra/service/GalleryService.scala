package by.arhor.psra.service

import by.arhor.psra.dto.GalleryDto

trait GalleryService extends Service {

  override type DTO = GalleryDto
  override type  ID = String

}
