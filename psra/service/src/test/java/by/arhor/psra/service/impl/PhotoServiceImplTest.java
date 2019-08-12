package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.model.Comment;
import by.arhor.psra.model.Photo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PhotoServiceImplTest extends MockBeanProvider {

  @SpyBean
  private PhotoServiceImpl service;

  @Before
  public void setup() {
    when(modelMapper.map(photo, PhotoDto.class))
        .thenReturn(photoDto);
    when(modelMapper.map(photoDto, Photo.class))
        .thenReturn(photo);
    when(modelMapper.map(comment, CommentDto.class))
        .thenReturn(commentDto);
    when(modelMapper.map(commentDto, Comment.class))
        .thenReturn(comment);
  }

  @Test
  public void findCommentsByPhotoIdPositiveTest() {
    String mockId = "mock-id";
    List<Comment> mockComments = listOf(5, () -> comment);

    when(photoRepository.findById(mockId))
        .thenReturn(Optional.of(photo));
    when(photo.comments())
        .thenReturn(mockComments);

    List<CommentDto> result = service.findCommentsByPhotoId(mockId);

    assertThat(result, is(notNullValue()));
    assertThat(result, hasSize(5));

    result.forEach(dto -> {
      assertThat(dto, is(notNullValue()));
      assertThat(dto, is(equalTo(commentDto)));
    });

    verify(photoRepository)
        .findById(mockId);
    verify(photo)
        .comments();
    verify(modelMapper, times(5))
        .map(comment, CommentDto.class);
  }

  @Test
  public void findCommentsByPhotoIdNegativeTest() {
    when(photoRepository.findById(any(String.class)))
        .thenReturn(Optional.empty());

    String id = "test-id";

    try {
      service.findCommentsByPhotoId(id);
      fail();
    } catch (Throwable error) {
      assertThat(error, is(instanceOf(EntityNotFoundException.class)));

      if (error instanceof EntityNotFoundException) {
        EntityNotFoundException e = (EntityNotFoundException) error;

        assertThat(e.fieldName(), is(equalTo("ID")));
        assertThat(e.fieldValue(), is(equalTo(id)));
      } else {
        fail();
      }
    }

    verify(photoRepository)
        .findById(id);
  }

}
