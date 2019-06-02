package by.arhor.psra.service.impl

import java.util

import by.arhor.psra.exception.EntityNotFoundException
import by.arhor.psra.localization.Error
import by.arhor.psra.model.Gallery
import by.arhor.psra.repository.GalleryRepository
import by.arhor.psra.service.GalleryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GalleryServiceImpl @Autowired() (private val repository: GalleryRepository,
                                       private val userRepository: UserRepository) extends GalleryService {

  @Transactional(readOnly = true)
  override def findOne(id: String): Gallery = repository
    .findById(id)
    .orElseThrow { () => new EntityNotFoundException(Error.GALLERY_NOT_FOUND, "ID", id) }


  @Transactional(readOnly = true)
  override def findAll(): util.List[Gallery] = repository.findAll()

  @Transactional
  override def create(gallery: Gallery): Gallery = repository.insert(gallery)

  @Transactional
  override def update(gallery: Gallery): Gallery = repository
    .findById(gallery.getId)
    .map[Gallery] { repository save _ }
    .orElseThrow { () => new EntityNotFoundException(Error.GALLERY_NOT_FOUND, "ID", gallery.getId) }


  @Transactional
  override def delete(gallery: Gallery): Unit = repository delete
    repository
      .findById(gallery.getId)
      .orElseThrow { () => new EntityNotFoundException(Error.GALLERY_NOT_FOUND, "ID", gallery.getId) }

  @Transactional(readOnly = true)
  override def findGalleriesByUserId(uid: String): List[Gallery] = userRepository
    .findById(uid)
    .map[List[Gallery]] { _.getGalleries() }
    .orElseThrow { () => new EntityNotFoundException(Error.USER_NOT_FOUND, "ID", uid) }

}
