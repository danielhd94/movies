package com.danielhd.movies.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielhd.movies.dao.IGenderRepository;
import com.danielhd.movies.entities.Gender;
import com.danielhd.movies.entities.Movie;
import com.danielhd.movies.interfaces.IGenderService;

@RestController
public class GenderController {

	private IGenderService service;

	public GenderController(IGenderService service) {
		this.service = service;
	}

	@PostMapping("gender")
	public Long save(@RequestParam String name) {
		Gender gender = new Gender();
		gender.setName(name);

		service.save(gender);

		return gender.getId();
	}

	@GetMapping("gender/{id}")
	public String findById(@PathVariable(name = "id") Long id) {
		return service.findById(id).getName();
	}

	@GetMapping("gender/all")
	public List<Gender> findAll() {
		List<Gender> genders = service.findAll();
		return genders;
	}

}
