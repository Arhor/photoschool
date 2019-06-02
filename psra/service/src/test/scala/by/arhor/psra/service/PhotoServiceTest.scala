package by.arhor.psra.service

import java.time.LocalDateTime

import by.arhor.psra.model.{Comment, Photo, Tag, User}
import by.arhor.psra.repository.PhotoRepository
import by.arhor.psra.repository.model.{Photo, Tag, User}
import by.arhor.psra.service.impl.PhotoServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.{MockBean, SpyBean}
import org.springframework.test.context.junit4.SpringRunner

import scala.collection.JavaConverters._

@RunWith(classOf[SpringRunner])
class PhotoServiceTest {

  @MockBean private var photoRepository: PhotoRepository   = _
  @SpyBean  private var photoService: PhotoServiceImpl = _


  @Test
  def test(): Unit = {
    val photo = new Photo()

    photo.name = "Test name"
    photo.description = "Test description"
    photo.path = "test/path.jpg"
    photo.dateTimeCreated = LocalDateTime.now()
    photo.dateTimeUpdated = LocalDateTime.now()
    photo.tags = Set(Tag("one"), Tag("two"))

    val comment = new Comment()

    comment.dateTimeCreated = LocalDateTime.now()
    comment.dateTimeUpdated = LocalDateTime.now()
    comment.content = "some interesting comment!"

    val user = new User()

    user.username = "Max"
    user.password = "pass"

    comment.user = user

    photo.comments = List(comment)



    Mockito.when(photoRepository.findAll()).thenReturn(List(photo).asJava)

    println()
    println()
    println()
    val dtos = photoService.findAll()
    dtos.forEach(println)
    println()
    println()
    println()
  }

}
