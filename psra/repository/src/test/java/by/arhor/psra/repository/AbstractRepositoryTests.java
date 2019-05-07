package by.arhor.psra.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class AbstractRepositoryTests {
	
	@Autowired protected TagRepository tagRepository;
	@Autowired protected PhotoRepository photoRepository;
	@Autowired protected GalleryRepository galleryRepository;
	@Autowired protected UserRepository userRepository;
	
	@SpringBootApplication
	static class TestApplicationRunner {
		public static void main(String[] args) {
			SpringApplication.run(TestApplicationRunner.class, args);
		}
	}

}
