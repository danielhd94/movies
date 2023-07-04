package com.danielhd.movies.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.danielhd.movies.entities.Movie;

public interface IMoviesService {

	public void save(Movie movie);

	public Movie findById(Long id);

	public List<Movie> findAll();
	
	public Page<Movie> findAll(Pageable pageable);

	public void delete(Long id);
}
