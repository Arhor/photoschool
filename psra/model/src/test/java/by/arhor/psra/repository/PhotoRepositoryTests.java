package by.arhor.psra.repository;

import by.arhor.psra.model.Photo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PhotoRepositoryTests extends AbstractRepositoryTests {

	private static final String[] tags = {"Sport", "Food", "Games"};

	private List<Photo> generatePhotos() {
		List<Photo> photos = new ArrayList<>();
		int i;
		for (i = 0; i < tags.length; i++) {
			Photo p = new Photo();
			p.setName("test_photo_" + i);
			p.setDescription("description_" + i);
			p.setPath("/user/photo_" + i + ".png");
			p.setTags(Set.of(tags[i]));
			photos.add(p);
		}
		Photo p = new Photo();
		p.setName("test_photo_" + i);
		p.setDescription("description_" + i);
		p.setPath("/user/photo_" + i + ".png");
		p.setTags(Set.of(tags));
		photos.add(p);
		return photos;
	}

	@Before
	public void setup() {
		photoRepository.insert(generatePhotos());
	}
	
	@After
	public void teardown() {
		photoRepository.deleteAll();
	}

	@Test
	public void findByTagTest() {
		for (String tag : tags) {
			List<Photo> photo = photoRepository.findByTag(tag);
			System.out.println("current tag: " + tag);
			System.out.println(photo);
		}
	}

}
