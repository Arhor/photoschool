package by.arhor.psra.repository

import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractRepositoryTests {
	
	@Autowired protected var photoRepository:   PhotoRepository   = _
	@Autowired protected var galleryRepository: GalleryRepository = _
	@Autowired protected var userRepository:    UserRepository    = _
	@Autowired protected var commentRepository: CommentRepository = _

}
