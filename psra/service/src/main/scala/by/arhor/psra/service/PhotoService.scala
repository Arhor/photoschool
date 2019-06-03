package by.arhor.psra.service

import by.arhor.psra.model.{Photo, Tag, User}

trait PhotoService extends Service {

  override type Entity = Photo
  override type ID  = String

  def findPhotosByTag(tag: Tag, requester: User): List[Photo]

}
