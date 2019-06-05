package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.PhotoDTO
import by.arhor.psra.model.User

trait PhotoService extends Service {

  override type DTO = PhotoDTO
  override type ID  = String

  def findPhotosByTag(tag: String, requester: User): util.List[PhotoDTO]

}
