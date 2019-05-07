package by.arhor.psra.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import by.arhor.psra.repository.model.Tag;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class TagRepositoryTests extends AbstractRepositoryTests {
	
	private List<Tag> tags = Arrays.asList(
			new Tag(null, "tag 1"),
			new Tag(null, "tag 2"),
			new Tag(null, "tag 3")
	);
	
	@Before
	public void methodSetUp() {
		tagRepository.insert(tags);
	}
	
	@After
	public void methodTearDown() {
		tagRepository.deleteAll();
	}

	@Test
	public void findAllTest() {
		var tags = tagRepository.findAll();
		
		assertThat(tags, is(notNullValue()));
		assertThat(tags, hasSize(3));
		tags.forEach(tag -> assertThat(tag, is(notNullValue())));
	}

}
