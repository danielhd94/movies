package com.danielhd.movies.dao;

import org.springframework.data.repository.CrudRepository;

import com.danielhd.movies.entities.Gender;

public interface IGenderRepository extends CrudRepository<Gender, Long> {

}
