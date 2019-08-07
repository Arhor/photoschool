package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.model.Gallery

trait GalleryService extends Service[Gallery, GalleryDto, String] {

  def findGalleriesByUserId(uid: String): util.List[GalleryDto]

}
