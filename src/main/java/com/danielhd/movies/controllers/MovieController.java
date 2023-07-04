package com.danielhd.movies.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.danielhd.movies.entities.Actor;
import com.danielhd.movies.entities.Movie;
import com.danielhd.movies.interfaces.IActorService;
import com.danielhd.movies.interfaces.IFileService;
import com.danielhd.movies.interfaces.IGenderService;
import com.danielhd.movies.interfaces.IMoviesService;

import jakarta.validation.Valid;

@Controller
public class MovieController {

	private IMoviesService service;
	private IGenderService genderService;
	private IActorService actorService;
	private IFileService fileService;

	public MovieController(IMoviesService service, IGenderService genderService, IActorService actorService,
			IFileService fileService) {
		this.service = service;
		this.genderService = genderService;
		this.actorService = actorService;
		this.fileService = fileService;
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
		Movie movie = service.findById(id);
		String ids = "";

		for (Actor actor : movie.getProtagonists()) {
			if ("".equals(ids)) {
				ids = actor.getId().toString();
			} else {
				ids = ids + "," + actor.getId().toString();
			}
		}
		model.addAttribute("movie", movie);
		model.addAttribute("ids", ids);
		model.addAttribute("genders", genderService.findAll());
		model.addAttribute("actors", actorService.findAll());
		model.addAttribute("title", "Edit Movie");
		return "movie";
	}

	@PostMapping("/movie")
	public String save(@Valid Movie movie, BindingResult br, @ModelAttribute(name = "ids") String ids, Model model,
			@RequestParam("file") MultipartFile image) {

		if (br.hasErrors()) {
			model.addAttribute("genders", genderService.findAll());
			model.addAttribute("actors", actorService.findAll());
			return "movie";
		}

		if (!image.isEmpty()) {
			String file = movie.getName() + getExtension(image.getOriginalFilename());
			System.out.println("file: " + file);
			movie.setImage(file);
			try {
				fileService.save(file, image.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			movie.setImage("_default.jpeg");
		}

		if (ids != null && !"".equals(ids)) {
			List<Long> idActores = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
			List<Actor> actors = actorService.findAllById(idActores);
			movie.setProtagonists(actors);
		}

		service.save(movie);
		return "redirect:home";
	}

	private String getExtension(String file) {
		return file.substring(file.lastIndexOf("."));
	}

	@GetMapping({ "/", "/home", "/index" })
	public String home(Model model) {
		model.addAttribute("movies", service.findAll());
		/*
		 * model.addAttribute("msj", "La app está en mantenimiento");
		 * model.addAttribute("messageType", "danger");
		 */
		return "home";
	}

	@GetMapping({ "/list" })
	public String list(Model model, @RequestParam(required = false) String msj,
			@RequestParam(required = false) String messageType) {
		model.addAttribute("title", "movie list");
		model.addAttribute("movies", service.findAll());
		
		if(!"".equals(messageType) && !"".equals(msj)) {
			model.addAttribute("msj", msj);
			model.addAttribute("messageType", messageType);
		}
		return "list";
	}

	@GetMapping("/movie/{id}/delete")
	public String delete(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAtt) {
		service.delete(id);

		redirectAtt.addAttribute("msj", "The movie was deleted successfully");
		redirectAtt.addAttribute("messageType", "success");
		return "redirect:/list";
	}
}
