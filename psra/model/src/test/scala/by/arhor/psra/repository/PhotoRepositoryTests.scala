package by.arhor.psra.repository
import java.util

import by.arhor.psra.model.enums.Roles.ROLE_GUEST
import by.arhor.psra.model.{Photo, User}
import org.hamcrest.Matchers.{is, notNullValue}
import org.junit.Assert._
import org.junit.runner.RunWith
import org.junit.{After, Before, Test}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest
@EnableAutoConfiguration
class PhotoRepositoryTests extends AbstractRepositoryTests {

	
	@Before
	def methodSetUp(): Unit = {

		val tags: util.Set[String] = new util.HashSet[String]

		tags.add("one")
		tags.add("two")
		tags.add("three")

		val photo = new Photo
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

		photos.forEach { assertThat(_, is(notNullValue())) }
		photos.forEach(println)
	}

	@Test
	def findAllByTagTest(): Unit = {

		photoRepository.findPhotosByTag("one").forEach { print }
	}
	
	@Test
	def userRepoTest(): Unit = {
		val user = new User
		user.setRole(ROLE_GUEST)
		user.setUsername("tester")
		userRepository.insert(user)

		println(userRepository findByUsername "tester")
	}

	@Test
	def test(): Unit = {
		galleryRepository.findAll().forEach(println)
	}

}
