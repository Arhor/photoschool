package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.localization.ErrorLabel;
import by.arhor.psra.model.Comment;
import by.arhor.psra.repository.CommentRepository;
import by.arhor.psra.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.arhor.psra.localization.ErrorLabel.COMMENT_NOT_FOUND;

@Service
@Transactional
public class CommentServiceImpl
    extends AbstractService<Comment, CommentDto, String>
    implements CommentService {

  private final CommentRepository repository;

  @Autowired
  public CommentServiceImpl(CommentRepository repository, ModelMapper mapper) {
    super(repository, mapper, CommentDto.class);
    this.repository = repository;
  }

  @Override
  protected ErrorLabel notFoundLabel() {
    return COMMENT_NOT_FOUND;
  }

  @Override
  public CommentDto create(CommentDto dto) {
    final var comment = mapper.map(dto, Comment.class);
    final var created = repository.insert(comment);
    return mapper.map(created, CommentDto.class);
  }

  @Override
  public CommentDto update(CommentDto dto) {
    final var comment = repository
      .findById(dto.getId())
      .orElseThrow (notFoundBy("ID", dto.getId()));

    comment.setText(dto.getText());

    final var updated = repository.save(comment);
    return mapper.map(updated, CommentDto.class);
  }
}
