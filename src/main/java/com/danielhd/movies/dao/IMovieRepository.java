package com.danielhd.movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielhd.movies.entities.Movie;

public interface IMovieRepository extends JpaRepository<Movie, Long> {

}
