package com.danielhd.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danielhd.movies.dao.IMovieRepository;
import com.danielhd.movies.entities.Movie;
import com.danielhd.movies.interfaces.IMoviesService;

@Service
public class MovieService implements IMoviesService {

	@Autowired
	private IMovieRepository movieRepositry;

	@Override
	public void save(Movie movie) {
		movieRepositry.save(movie);
	}

	@Override
	public Movie findById(Long id) {
		return movieRepositry.findById(id).orElse(null);
	}

	@Override
	public List<Movie> findAll() {
		return (List<Movie>) movieRepositry.findAll();
	}

	@Override
	public void delete(Long id) {
		movieRepositry.deleteById(id);
		
	}

	@Override
	public Page<Movie> findAll(Pageable pageable) {
		return movieRepositry.findAll(pageable);
	}

}
