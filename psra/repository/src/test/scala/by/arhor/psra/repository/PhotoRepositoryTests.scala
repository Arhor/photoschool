package by.arhor.psra.repository
import org.hamcrest.Matchers.is
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert._

import by.arhor.psra.repository.model.{Photo, Tag, User}
import org.junit.{After, Before, Test}
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest
@EnableAutoConfiguration
class PhotoRepositoryTests extends AbstractRepositoryTests {
	
	val tags: Set[Tag] = Set(Tag("one"), Tag("two"), Tag("three"))
	
	@Before
	def methodSetUp(): Unit = {
		val photo = new Photo()
		photo.setPath("/user/photo_1.png")
		photo.setTags(tags)
		photoRepository.insert(photo)
	}
	
	@After
	def methodTearDown(): Unit = {
		photoRepository.deleteAll()
	}

	@Test
	def findAllTest(): Unit = {
		val photos: java.util.List[Photo] = photoRepository.findAll()

		assertThat(photos, is(notNullValue()))
//		assertThat(photos, hasSize(1))

		photos.forEach(photo => assertThat(photo, is(notNullValue())))
		photos.forEach(println)
	}
	
	@Test
	def userRepoTest(): Unit = {
		val user = new User()
		user.setUsername("tester")
		userRepository.insert(user)

		println((userRepository findByUsername "tester").get.username)
	}

}
