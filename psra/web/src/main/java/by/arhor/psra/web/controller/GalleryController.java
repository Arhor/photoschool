package by.arhor.psra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.arhor.psra.dto.GalleryDto;
import by.arhor.psra.service.GalleryService;

@RestController
@RequestMapping("/galleries")
public class GalleryController {
	
	private GalleryService service;
	
	@Autowired
	public GalleryController(GalleryService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/{id}", produces = "apllication/json")
	public GalleryDto getGlleryById(@PathVariable("id") String id) {
		return service.findOne(id);
	}

}
