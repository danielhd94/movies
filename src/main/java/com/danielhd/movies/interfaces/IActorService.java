package com.danielhd.movies.interfaces;

import java.util.List;

import com.danielhd.movies.entities.Actor;

public interface IActorService {
	
	public List<Actor> findAll();
	
	public List<Actor> findAllById(List<Long> id);
	
}
