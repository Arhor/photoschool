package by.arhor.psra.service

import by.arhor.psra.dto.PhotoDto

trait PhotoService extends Service {

  override type DTO = PhotoDto
  override type ID  = String

}
