package com.danielhd.movies.dao;

import org.springframework.data.repository.CrudRepository;

import com.danielhd.movies.entities.Movie;

public interface IMovieRepository extends CrudRepository<Movie, Long> {

}
