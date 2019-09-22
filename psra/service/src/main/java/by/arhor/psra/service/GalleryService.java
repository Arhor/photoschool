package by.arhor.psra.service;


import by.arhor.psra.dto.GalleryDto;

import java.util.List;

public interface GalleryService extends Service<GalleryDto, String> {

  List<GalleryDto> findGalleriesByUserId(String uid);

}
