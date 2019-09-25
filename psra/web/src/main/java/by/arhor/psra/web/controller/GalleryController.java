package by.arhor.psra.web.controller;

import by.arhor.psra.dto.GalleryDto;
import by.arhor.psra.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping(path = "/galleries")
public class GalleryController {

	private final GalleryService service;

	@Autowired
	public GalleryController(GalleryService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
	public GalleryDto getGalleryById(@PathVariable("id") String id) {
		return service.findOne(id);
	}
}
