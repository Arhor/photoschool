package by.arhor.psra.service.reactive.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.model.Comment;
import by.arhor.psra.repository.reactive.CommentRepository;
import by.arhor.psra.service.reactive.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CommentServiceImpl
    extends AbstractService<Comment, CommentDto, String>
    implements CommentService {

  @Autowired
  public CommentServiceImpl(CommentRepository repository, ModelMapper mapper) {
    super(repository, mapper, CommentDto.class);
  }


  @Override
  public Mono<CommentDto> create(CommentDto dto) {
    return null;
  }

  @Override
  public Mono<CommentDto> update(CommentDto dto) {
    return repository
        .findById(dto.getId())
        .map(comment -> {
          comment.setText(dto.getText());
          return comment;
        })
        .flatMap(repository::save)
        .map(this::toDto);
  }

}
