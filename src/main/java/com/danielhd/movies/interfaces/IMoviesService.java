package com.danielhd.movies.interfaces;

import java.util.List;

import com.danielhd.movies.entities.Movie;

public interface IMoviesService {

	public void save(Movie movie);

	public Movie findById(Long id);

	public List<Movie> findAll();

	public void delete(Long id);
}
