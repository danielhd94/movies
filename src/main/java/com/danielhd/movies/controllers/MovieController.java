package com.danielhd.movies.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.danielhd.movies.entities.Actor;
import com.danielhd.movies.entities.Movie;
import com.danielhd.movies.interfaces.IActorService;
import com.danielhd.movies.interfaces.IGenderService;
import com.danielhd.movies.interfaces.IMoviesService;

@Controller
public class MovieController {

	private IMoviesService service;
	private IGenderService genderService;
	private IActorService actorService;

	public MovieController(IMoviesService service, IGenderService genderService, IActorService actorService) {
		this.service = service;
		this.genderService = genderService;
		this.actorService = actorService;
	}

	@GetMapping("/movie")
	public String create(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);
		model.addAttribute("genders", genderService.findAll());
		System.out.print("actors: " + actorService.findAll());
		model.addAttribute("actors", actorService.findAll());
		model.addAttribute("title", "New Movie");
		return "movie";
	}

	@GetMapping("/movie/{id}")
	public String edit(@PathVariable(name = "id") Long id, Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);
		model.addAttribute("genders", genderService.findAll());
		model.addAttribute("actors", actorService.findAll());
		model.addAttribute("title", "Edit Movie");
		return "movie";
	}

	@PostMapping("/movie")
	public String save(Movie movie, @ModelAttribute(name="ids") String ids) {
		List<Long> idActores = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
		List<Actor> actors = actorService.findAllById(idActores);
		movie.setProtagonists(actors);
		service.save(movie);
		return "redirect:home";
	}

	@GetMapping({ "/", "/home", "/index" })
	public String home() {
		return "home";
	}
}
