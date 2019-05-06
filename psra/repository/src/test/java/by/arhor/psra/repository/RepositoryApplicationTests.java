//package by.arhor.psra.repository;

//import static java.util.stream.Collectors.toList;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Stream;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

//import by.arhor.psra.repository.model.Gallery;
//import by.arhor.psra.repository.model.Photo;
//import by.arhor.psra.repository.model.Tag;
//import by.arhor.psra.repository.model.User;
//import by.arhor.psra.repository.model.Gallery.Type;
//import by.arhor.psra.repository.model.User.Role;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RepositoryApplicationTests {
//	
//	@Autowired
//	private UserRepository uRepository;
//	
//	@Autowired
//	private GalleryRepository gRepository;
//	
//	@Autowired
//	private PhotoRepository pRepository;
//	
//	@Autowired
//	private TagRepository tRepository;
//
//	@Test
//	public void contextLoads() {
//		User user = new User();
//		
//		user.setEmail("Max");
//		user.setPassword("password");	
//		user.setRole(Role.ROLE_ADMIN);
//		
//		var tags = generateEntities(10, Tag::new);
//		tRepository.insert(tags);
//		
//		var photo = new Photo(null, "test photo path", tags);
//		var photos = new ArrayList<Photo>();
//		photos.add(photo);
//		pRepository.insert(photos);
//		
//		var gallery = new Gallery(null, "test gallery", Type.PUBLIC, photos);
//		var galleries = new ArrayList<Gallery>();
//		galleries.add(gallery);
//		gRepository.insert(galleries);
//		
//		user.setGalleries(galleries);
//		
//		uRepository.insert(user);
//		
//		uRepository.findAll().forEach(u -> {
//			System.out.println(u);
//			u.getGalleries().forEach(System.out::println);
//		});
//	}
//	
//	private <T> List<T> generateEntities(int count, Function<String, T> supplier) {
//		return Stream.iterate(1, n -> n + 1)
//					 .limit(count)
//					 .map(String::valueOf)
//					 .map(supplier)
//					 .collect(toList());
//	}
//
//}
