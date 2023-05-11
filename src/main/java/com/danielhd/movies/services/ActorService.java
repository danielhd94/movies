package com.danielhd.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielhd.movies.dao.IActorRepository;
import com.danielhd.movies.entities.Actor;
import com.danielhd.movies.interfaces.IActorService;

@Service
public class ActorService implements IActorService {
	
	@Autowired
	private IActorRepository repo;

	@Override
	public List<Actor> findAll() {
		return (List<Actor>) repo.findAll();
	}

	@Override
	public List<Actor> findAllById(List<Long> id) {
		return (List<Actor>) repo.findAllById(id);
	}

}
