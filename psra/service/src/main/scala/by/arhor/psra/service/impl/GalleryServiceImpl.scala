package by.arhor.psra.service.impl

import java.util
import java.util.stream.Collectors.toList

import by.arhor.psra.dto.GalleryDto
import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.ErrorLabel
import by.arhor.psra.model.Gallery
import by.arhor.psra.repository.{GalleryRepository, UserRepository}
import by.arhor.psra.service.GalleryService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GalleryServiceImpl @Autowired() (
  private val repository: GalleryRepository,
  private val userRepository: UserRepository,
  override protected val modelMapper: ModelMapper
) extends GalleryService {

  @Transactional(readOnly = true)
  override def findOne(id: String): GalleryDto = repository
    .findById(id)
    .map[GalleryDto] { _.as[GalleryDto] }
    .orElseThrow {
      () => new EntityNotFoundException(ErrorLabel.GALLERY_NOT_FOUND, "ID", id)
    }


  @Transactional(readOnly = true)
  override def findAll(): util.List[GalleryDto] =
    repository
      .findAll
      .stream
      .map[GalleryDto] { _.as[GalleryDto] }
      .collect(toList())

  @Transactional(readOnly = true)
  override def findGalleriesByUserId(uid: String): util.List[GalleryDto] =
    userRepository
      .findById(uid)
      .map[util.List[GalleryDto]] { user =>
        user
          .getGalleries
          .stream
          .map[GalleryDto] { _.as[GalleryDto] }
          .collect(toList())
      }
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.USER_NOT_FOUND, "ID", uid)
      }


  override def create(dto: GalleryDto): GalleryDto =
    Some(dto)
      .map[Gallery] { _.as[Gallery] }
      .map[Gallery] { repository insert _ }
      .map[GalleryDto] { _.as[GalleryDto] }
      .get

  override def update(dto: GalleryDto): GalleryDto = ???
//    repository
//      .findById(dto.getId)
//      .map[Gallery] { _ => mapToEntity(dto) }
//      .map[Gallery] { repository save _ }
//      .map[GalleryDto] { mapToDto }
//      .orElseThrow {
//        () => new EntityNotFoundException(ErrorLabel.GALLERY_NOT_FOUND, "ID", dto.getId)
//      }

  override def delete(gallery: GalleryDto): Unit =
    repository
      .findById(gallery.getId)
      .map[Unit] { repository delete _ } // TODO: does it work?
      .orElseThrow {
        () => new EntityNotFoundException(ErrorLabel.GALLERY_NOT_FOUND, "ID", gallery.getId)
      }

}
