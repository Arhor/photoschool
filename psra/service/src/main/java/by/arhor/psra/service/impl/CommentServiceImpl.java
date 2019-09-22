package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.model.Comment;
import by.arhor.psra.repository.CommentRepository;
import by.arhor.psra.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.arhor.psra.localization.ErrorLabel.COMMENT_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  private final CommentRepository repository;
  private final ModelMapper mapper;

  @Autowired
  public CommentServiceImpl(CommentRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  @Transactional(readOnly = true)
  public CommentDto findOne(String id) {
    return repository
        .findByIdAndEnabledTrue(id)
        .map(comment -> mapper.map(comment, CommentDto.class))
        .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND, "ID", id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<CommentDto> findAll() {
    return repository
        .findAll()
        .stream()
        .map(comment -> mapper.map(comment, CommentDto.class))
        .collect(toList());
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
      .findByIdAndEnabledTrue(dto.getId())
      .orElseThrow (() -> new EntityNotFoundException(COMMENT_NOT_FOUND, "ID", dto.getId()));

    comment.setText(dto.getText());

    final var updated = repository.save(comment);
    return mapper.map(updated, CommentDto.class);
  }

  @Override
  public void delete(CommentDto dto) {
    final var comment = repository
      .findByIdAndEnabledTrue(dto.getId())
      .orElseThrow(() -> new EntityNotFoundException(COMMENT_NOT_FOUND, "ID", dto.getId()));

    comment.setEnabled(false);

    repository.save(comment);
  }
}
