package by.arhor.psra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.arhor.psra.dto.TagDto;
import by.arhor.psra.service.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {
	
	private TagService service;
	
	@Autowired
	public TagController(TagService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/{id}" ,produces = "application/json")
	public TagDto getTagById(@PathVariable("id") String id) {
		return service.findOne(id);
	}

}
