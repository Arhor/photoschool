package by.arhor.psra.service.impl;

import by.arhor.psra.dto.GalleryDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.model.Gallery;
import by.arhor.psra.repository.GalleryRepository;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.service.GalleryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.arhor.psra.localization.ErrorLabel.GALLERY_NOT_FOUND;
import static by.arhor.psra.localization.ErrorLabel.USER_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
class GalleryServiceImpl implements GalleryService {

  private final GalleryRepository repository;
  private final UserRepository userRepository;
  private final ModelMapper mapper;

  @Autowired
  public GalleryServiceImpl(
      GalleryRepository repository,
      UserRepository userRepository,
      ModelMapper mapper) {

    this.repository = repository;
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  @Transactional(readOnly = true)
  public GalleryDto findOne(String id) {
    return repository
        .findByIdAndEnabledTrue(id)
        .map(gallery -> mapper.map(gallery, GalleryDto.class))
        .orElseThrow(() -> new EntityNotFoundException(GALLERY_NOT_FOUND, "ID", id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<GalleryDto> findAll() {
    return repository
        .findAll()
        .stream()
        .map(gallery -> mapper.map(gallery, GalleryDto.class))
        .collect(toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<GalleryDto> findGalleriesByUserId(String uid) {
    return userRepository
        .findByIdAndEnabledTrue(uid)
        .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND, "ID", uid))
        .getGalleries()
        .stream()
        .map(gallery -> mapper.map(gallery, GalleryDto.class))
        .collect(toList());
  }

  @Override
  public GalleryDto create(GalleryDto dto) {
    final var gallery = mapper.map(dto, Gallery.class);
    final var created = repository.insert(gallery);
    return mapper.map(created, GalleryDto.class);
  }

  @Override
  public GalleryDto update(GalleryDto dto) {
    final var gallery = repository
        .findByIdAndEnabledTrue(dto.getId())
        .orElseThrow(() -> new EntityNotFoundException(GALLERY_NOT_FOUND, "ID", dto.getId()));

    gallery.setName(dto.getName());

    final var updated = repository.save(gallery);
    return mapper.map(updated, GalleryDto.class);
  }

  @Override
  public void delete(GalleryDto dto) {
    final var gallery = repository
        .findByIdAndEnabledTrue(dto.getId())
        .orElseThrow(() -> new EntityNotFoundException(GALLERY_NOT_FOUND, "ID", dto.getId()));

    gallery.setEnabled(false);

    repository.save(gallery);
  }


}
