package by.arhor.psra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.arhor.psra.dto.PhotoDto;
import by.arhor.psra.service.PhotoService;

@RestController
@RequestMapping("/photos")
public class PhotoController {

	private PhotoService service;
	
	@Autowired
	public PhotoController(PhotoService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public PhotoDto getPhotoById(@PathVariable("id") String id) {
		return service.findOne(id);
	}
	
}
