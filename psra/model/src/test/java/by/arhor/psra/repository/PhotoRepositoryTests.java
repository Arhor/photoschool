package by.arhor.psra.repository;

import by.arhor.psra.model.Photo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PhotoRepositoryTests extends AbstractRepositoryTests {

	private static final String[] tags = {"Sport", "Food", "Games"};

	@SafeVarargs
	private static <T> Set<T> setOf(T...args) {
		Set<T> set = new HashSet<>();
		Collections.addAll(set, args);
		return set;
	}

	private List<Photo> generatePhotos() {
		List<Photo> photos = new ArrayList<>();
		int i;
		for (i = 0; i < tags.length; i++) {
			Photo p = new Photo();
			p.setName("test_photo_" + i);
			p.setDescription("description_" + i);
			p.setPath("/user/photo_" + i + ".png");
			p.setTags(setOf(tags[i]));
			photos.add(p);
		}
		Photo p = new Photo();
		p.setName("test_photo_" + i);
		p.setDescription("description_" + i);
		p.setPath("/user/photo_" + i + ".png");
		p.setTags(setOf(tags));
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
	public void findByAnyOfTagsTest() {
		for (String tag : tags) {
			List<Photo> photos = photoRepository.findByAnyOfTags(new String[] {tag});
			assertThat(photos, notNullValue());
			assertThat(photos, hasSize(2));
		}
	}

	@Test
	public void findByAllOfTagsTest() {
		List<Photo> photos = photoRepository.findByAllOfTags(tags);
		assertThat(photos, notNullValue());
		assertThat(photos, hasSize(1));
	}

	@Test
	public void findByIdAndEnabledTrueTest() {
		List<Photo> photos = photoRepository.findAll();

		assertThat(photos, notNullValue());

		photos.forEach(p -> {
			Photo photo_1 = photoRepository.findById(p.id()).get();
			Photo photo_2 = photoRepository.findByIdAndEnabledTrue(p.id()).get();

			assertThat(photo_1, notNullValue());
			assertThat(photo_2, notNullValue());
			assertThat(photo_1, equalTo(photo_2));

			System.out.printf("%s - %s%n", photo_1, photo_2);
		});
	}

	@Test
	public void findAllEnabledTrue() {
		PageRequest request = PageRequest.of(0, 10);
		Page<Photo> page = photoRepository.findAllByEnabledTrue(request);

		assertThat(page, notNullValue());
		assertThat(page.isEmpty(), equalTo(false));

		page.get().forEach(System.out::println);
	}

}
