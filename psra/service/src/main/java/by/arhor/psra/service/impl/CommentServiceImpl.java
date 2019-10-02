package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.localization.ErrorLabel;
import by.arhor.psra.model.Comment;
import by.arhor.psra.repository.CommentRepository;
import by.arhor.psra.service.CommentService;
import by.arhor.psra.util.MappingDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static by.arhor.psra.localization.ErrorLabel.COMMENT_NOT_FOUND;

@Service
@Transactional
public class CommentServiceImpl
    extends AbstractService<Comment, CommentDto, String>
    implements CommentService {

  @Autowired
  public CommentServiceImpl(CommentRepository repository,
                            MappingDelegate mapper) {
    super(repository, mapper, CommentDto.class);
  }

  @Override
  protected ErrorLabel notFoundLabel() {
    return COMMENT_NOT_FOUND;
  }

  @Override
  public CommentDto create(CommentDto dto) {
    throw new UnsupportedOperationException(
        "Comment creation available only for related entity");
  }

  @Override
  public CommentDto update(CommentDto dto) {
    Objects.requireNonNull(dto);
    final var comment = repository
      .findById(dto.getId())
      .orElseThrow (() -> new EntityNotFoundException(COMMENT_NOT_FOUND, "ID", dto.getId()));

    comment.setText(dto.getText());

    final var updated = repository.save(comment);
    return mapper.map(updated, CommentDto.class);
  }
}
