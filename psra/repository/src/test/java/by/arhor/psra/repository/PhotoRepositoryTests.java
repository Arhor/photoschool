package by.arhor.psra.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import by.arhor.psra.repository.model.Photo;
import by.arhor.psra.repository.model.Tag;
import by.arhor.psra.repository.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PhotoRepositoryTests extends AbstractRepositoryTests {
	
	private List<Tag> tags = Arrays.asList(
			new Tag(),
			new Tag(),
			new Tag()
	);
	
	@Before
	public void methodSetUp() {
		Photo photo = new Photo();
		photo.setPath("/user/photo_1.png");
		photo.setTags(new HashSet<>(tags));
		photoRepository.insert(photo);
	}
	
	@After
	public void methodTearDown() {
		photoRepository.deleteAll();
	}

	@Test
	public void findAllTest() {
		var photos = photoRepository.findAll();
		
		assertThat(photos, is(notNullValue()));
		assertThat(photos, hasSize(1));
		photos.forEach(photo -> assertThat(photo, is(notNullValue())));
		photos.forEach(System.out::println);
	}
	
	@Test
	public void userRepoTest() {
		User user = new User();
		user.setUsername("tester");
		userRepository.insert(user);
		
		System.out.println(userRepository.findByUsername("tester"));
	}

}
