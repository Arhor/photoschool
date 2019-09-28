package by.arhor.psra.repository;

import by.arhor.psra.model.Gallery;
import by.arhor.psra.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static by.arhor.psra.Predef.range;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class UserRepositoryTest extends AbstractRepositoryTest {

  private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

  @Before
  public void setup() {
    userRepository.insert(generateUsers());
  }

  @After
  public void teardown() {
    userRepository.deleteAll();
  }

  @Test
  public void addGalleryTest() {
    var gallery = new Gallery();
    gallery.setName("test gallery");

    gallery = galleryRepository.insert(gallery);

    var user = new User();
    user.setUsername("test username");
    user.setPassword("test password");
    user.setEmail("test@mail.org");
    user.setName("Max");

    log.info("User before create: {}", user);
    user = userRepository.insert(user);
    log.info("User after create: {}", user);

    user.addGallery(gallery);
    user = userRepository.save(user);

    log.info("User galleries: {}", user.getGalleries());
  }

  @Test
  public void findByUsernamePositiveTest() {
    for (var user : userRepository.findAll()) {
      assertThat(user, is(notNullValue()));

      final var found = userRepository.findByUsername(user.getUsername());
      assertTrue(
          found.isPresent()
      );
      log.info("Fetched user: {}", found.get());
    }
  }

  @Test
  public void findByUsernameDisabledTest() {
    for (var user : userRepository.findAll()) {
      assertThat(user, is(notNullValue()));
      user.setEnabled(false);
      userRepository.save(user);

      assertTrue(
          userRepository
              .findByUsername(user.getUsername())
              .isEmpty()
      );
    }
  }

  @Test
  public void existsByUsernamePositiveTest() {
    for (var user : userRepository.findAll()) {
      assertThat(user, is(notNullValue()));

      assertTrue(
          userRepository.existsByUsername(user.getUsername())
      );
    }
  }

  @Test
  public void existsByUsernameNegativeTest() {
    assertFalse(
        userRepository.existsByUsername("not_exists")
    );
  }

  private List<User> generateUsers() {
    final var users = new ArrayList<User>();

    for (var i : range(1, 10)) {
      final var user = new User();
      user.setUsername("User_" + i);
      user.setPassword("Pass_" + i);
      user.setEmail("test_" + i + ".mail@domen.org");
      users.add(user);
    }

    return users;
  }
}
