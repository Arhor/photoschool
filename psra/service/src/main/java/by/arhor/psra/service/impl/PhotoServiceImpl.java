package by.arhor.psra.service.impl;

import by.arhor.psra.dto.CommentDto;
import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.exception.EntityNotFoundException;
import by.arhor.psra.model.Comment;
import by.arhor.psra.model.Photo;
import by.arhor.psra.repository.CommentRepository;
import by.arhor.psra.repository.PhotoRepository;
import by.arhor.psra.repository.UserRepository;
import by.arhor.psra.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.arhor.psra.localization.ErrorLabel.PHOTO_NOT_FOUND;
import static by.arhor.psra.localization.ErrorLabel.USER_NOT_FOUND;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
class PhotoServiceImpl implements PhotoService {

	private final PhotoRepository repository;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final ModelMapper mapper;

	@Autowired
	public PhotoServiceImpl(
			PhotoRepository repository,
			CommentRepository commentRepository,
			UserRepository userRepository,
			ModelMapper mapper) {

		this.repository = repository;
		this.commentRepository = commentRepository;
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public PhotoDto findOne(String id) {
		return repository
				.findByIdAndEnabledTrue(id)
				.map(photo -> mapper.map(photo, PhotoDto.class))
				.orElseThrow(() -> new EntityNotFoundException(PHOTO_NOT_FOUND, "ID", id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhotoDto> findAll() {
		return repository
				.findAll()
				.stream()
				.map(photo -> mapper.map(photo, PhotoDto.class))
  			.collect(toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhotoDto> findPhotosByTag(String tag, String username) {
		return repository
				.findByAnyOfTags(new String[] { tag })
				.stream()
				.map(photo -> mapper.map(photo, PhotoDto.class))
				.collect(toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentDto> findCommentsByPhotoId(String pid) {
		return repository
				.findByIdAndEnabledTrue(pid)
				.orElseThrow(() -> new EntityNotFoundException(PHOTO_NOT_FOUND, "ID", pid))
				.getComments()
				.stream()
				.map(comment -> mapper.map(comment, CommentDto.class))
				.collect(toList());
	}

	@Override
	public CommentDto addCommentToPhoto(String photoId, String username, CommentDto dto) {
		final var photo = repository
			.findByIdAndEnabledTrue(photoId)
			.orElseThrow(() -> new EntityNotFoundException(PHOTO_NOT_FOUND, "ID", photoId));

		final var user = userRepository
			.findByUsername(username)
  		.orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND, "Username", username));

		final var comment = mapper.map(dto, Comment.class);
		comment.setUser(user);

		final var createdComment = commentRepository.insert(comment);
		photo.getComments().add(createdComment);

		repository.save(photo);

		return mapper.map(createdComment, CommentDto.class);
	}

	@Override
	public PhotoDto create(PhotoDto dto) {
		final var photo = mapper.map(dto, Photo.class);
		final var created = repository.insert(photo);
		return mapper.map(created, PhotoDto.class);
	}

	@Override
	public PhotoDto update(PhotoDto dto) {
		final var photo = repository
				.findByIdAndEnabledTrue(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException(PHOTO_NOT_FOUND, "ID", dto.getId()));

		photo.setName(dto.getName());
		photo.setDescription(dto.getDescription());
		photo.setPath(dto.getPath());
		photo.setTags(dto.getTags());

		final var created = repository.save(photo);
		return mapper.map(created, PhotoDto.class);
	}

	@Override
	public void delete(PhotoDto dto) {
		final var photo = repository
				.findByIdAndEnabledTrue(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException(PHOTO_NOT_FOUND, "ID", dto.getId()));

		photo.setEnabled(false);

		repository.save(photo);
	}
}
