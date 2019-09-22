package by.arhor.psra.repository;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;

abstract class AbstractRepositoryTest {
	
	@Autowired protected PhotoRepository photoRepository;
	@Autowired protected GalleryRepository galleryRepository;
	@Autowired protected UserRepository userRepository;
	@Autowired protected CommentRepository commentRepository;

	private Random rand = new Random();

	private String generatePath() {
		int depth = rand.nextInt(4);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= depth; i++) {
			builder.append("/");
			builder.append(RandomStringUtils.randomAlphabetic(4,7));
		}
		return builder.toString();
	}

}
