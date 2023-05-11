package com.danielhd.movies.interfaces;

import java.util.List;

import com.danielhd.movies.entities.Gender;

public interface IGenderService {

	public void save(Gender gender);

	public Gender findById(Long id);

	public void delete(Long id);

	public List<Gender> findAll();
}
