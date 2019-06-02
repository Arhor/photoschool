package by.arhor.psra.repository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

abstract class AbstractRepositoryTests {
	
	@Autowired protected var photoRepository: PhotoRepository = _
	@Autowired protected var galleryRepository: GalleryRepository = _
	@Autowired protected var userRepository: UserRepository = _

}
