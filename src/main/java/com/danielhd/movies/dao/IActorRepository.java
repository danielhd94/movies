package com.danielhd.movies.dao;

import org.springframework.data.repository.CrudRepository;

import com.danielhd.movies.entities.Actor;

public interface IActorRepository extends CrudRepository<Actor, Long> {

}
