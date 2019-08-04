package by.arhor.psra.service

import java.util

import by.arhor.psra.dto.{PhotoDto, UserDto}
import by.arhor.psra.model.Photo

trait PhotoService extends Service {

  override type M = Photo
  override type D = PhotoDto
  override type K = String

  def findPhotosByTag(tag: String, requester: UserDto): util.List[D]

}
