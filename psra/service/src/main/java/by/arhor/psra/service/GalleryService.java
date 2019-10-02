package by.arhor.psra.service;

import by.arhor.psra.dto.GalleryDto;

import java.util.List;

public interface GalleryService
    extends Service<GalleryDto, String>
          , CanCreate<GalleryDto>
          , CanUpdate<GalleryDto> {

  List<GalleryDto> findGalleriesByUserId(String uid);

}
