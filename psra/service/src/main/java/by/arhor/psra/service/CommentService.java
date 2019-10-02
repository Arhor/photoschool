package by.arhor.psra.service;

import by.arhor.psra.dto.CommentDto;

public interface CommentService
    extends Service<CommentDto, String>
          , CanUpdate<CommentDto> {
}
