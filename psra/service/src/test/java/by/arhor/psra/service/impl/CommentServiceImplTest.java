package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.model.Comment;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommentServiceImplTest extends MockBeanProvider {

  @SpyBean
  private CommentServiceImpl service;

  @Before
  public void setup() {
    when(modelMapper.map(comment, CommentDto.class))
        .thenReturn(commentDto);
    when(modelMapper.map(commentDto, Comment.class))
        .thenReturn(comment);
  }

  @Test
  public void findAllTest() {
    List<Comment> mockComments = listOf(3, () -> comment);

    when(commentRepository.findAll())
        .thenReturn(mockComments);

    List<CommentDto> result = service.findAll();

    assertThat(result, is(notNullValue()));
    assertThat(result, hasSize(3));

    result.forEach(dto -> {
      assertThat(dto, is(notNullValue()));
      assertThat(dto, is(equalTo(commentDto)));
    });

    verify(commentRepository)
        .findAll();
    verify(modelMapper, times(3))
        .map(comment, CommentDto.class);
  }

  @Test
  public void findOnePositiveTest() {
    String mockId = "mock-id";

    when(commentRepository.findByIdAndEnabledTrue(mockId))
        .thenReturn(Optional.of(comment));

    CommentDto result = service.findOne(mockId);

    assertThat(result, is(notNullValue()));
    assertThat(result, is(equalTo(commentDto)));

    verify(commentRepository)
        .findByIdAndEnabledTrue(mockId);
    verify(modelMapper)
        .map(comment, CommentDto.class);
  }

  @Test
  public void findOneNegativeTest() {
    when(commentRepository.findByIdAndEnabledTrue(any(String.class)))
        .thenReturn(Optional.empty());

    String id = "test-id";

    try {
      service.findOne(id);
      fail();
    } catch (Throwable error) {
      assertThat(error, is(instanceOf(EntityNotFoundException.class)));

      if (error instanceof EntityNotFoundException) {
        EntityNotFoundException e = (EntityNotFoundException) error;

        assertThat(e.getFieldName(), is(equalTo("ID")));
        assertThat(e.getFieldValue(), is(equalTo(id)));
      } else {
        fail();
      }
    }

    verify(commentRepository)
        .findByIdAndEnabledTrue(id);
  }

  @Test
  public void createPositiveTest() {
    when(commentRepository.insert(comment))
        .thenReturn(comment);

    CommentDto result = service.create(commentDto);

    assertThat(result, is(notNullValue()));
    assertThat(result, is(equalTo(commentDto)));

    verify(modelMapper)
        .map(commentDto, Comment.class);
    verify(commentRepository)
        .insert(comment);
    verify(modelMapper)
        .map(comment, CommentDto.class);
  }

}
