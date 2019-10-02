package by.arhor.psra.service;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.dto.PhotoDto;

import java.util.List;

public interface PhotoService
    extends Service<PhotoDto, String>
          , CanCreate<PhotoDto>
          , CanUpdate<PhotoDto> {

  List<PhotoDto> findPhotosByTag(String tag, String username);

  List<CommentDto> findCommentsByPhotoId(String pid);

  CommentDto addCommentToPhoto(String photoId, String username, CommentDto dto);

}
