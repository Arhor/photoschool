package by.arhor.psra.service

import by.arhor.psra.model.Photo

trait PhotoService extends Service {

  override type Entity = Photo
  override type ID  = String

}
