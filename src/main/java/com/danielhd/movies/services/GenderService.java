package com.danielhd.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielhd.movies.dao.IGenderRepository;
import com.danielhd.movies.entities.Gender;
import com.danielhd.movies.interfaces.IGenderService;

@Service
public class GenderService implements IGenderService {
	
	@Autowired
	private IGenderRepository genderRepositry;

	@Override
	public void save(Gender gender) {
		genderRepositry.save(gender);
	}

	@Override
	public Gender findById(Long id) {
		return genderRepositry.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		genderRepositry.deleteById(id);
	}

	@Override
	public List<Gender> findAll() {
		return (List<Gender>) genderRepositry.findAll();
	}

}
