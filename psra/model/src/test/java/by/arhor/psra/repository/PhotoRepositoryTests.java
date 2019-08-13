package by.arhor.psra.repository;

import by.arhor.psra.model.Roles;
import by.arhor.psra.model.Photo;
import by.arhor.psra.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PhotoRepositoryTests extends AbstractRepositoryTests {

	@Before
	public void setup() {
		Set<String> tags = new HashSet<>();

		tags.add("one");
		tags.add("two");
		tags.add("three");

		Photo photo = new Photo();

		photo.setPath("/user/photo_1.png");
		photo.setTags(tags);
		photoRepository.insert(photo);
	}
	
	@After
	public void teardown() {
		photoRepository.deleteAll();
	}

	@Test
	public void findAllTest() {
		List<Photo> photos = photoRepository.findAll();

		photos.forEach(System.out::println);
		assertThat(photos, is(notNullValue()));
//		assertThat(photos, hasSize(1))

		photos.forEach(photo -> assertThat(photo, is(notNullValue())) );
	}

	@Test
	public void findAllByTagTest() {
		String[] tags = {"one", "two", "five"};
		photoRepository
				.findByAllOfTags(tags)
				.forEach(System.out::println);
	}
	
	@Test
	public void userRepoTest() {
		User user = new User();

		user.setRole(Roles.ROLE_USER);
		user.setUsername("tester");
		userRepository.insert(user);

//		println(userRepository findByUsername "tester")
	}
}
