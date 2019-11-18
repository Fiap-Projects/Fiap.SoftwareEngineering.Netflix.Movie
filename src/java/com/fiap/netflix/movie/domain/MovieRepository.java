package com.fiap.netflix.movie.domain;

import org.springframework.data.repository.CrudRepository;

import com.fiap.netflix.movie.models.Movie;

public interface MovieRepository extends CrudRepository<Movies, Long>{
	
	Iterable<Movies> findAllByGenero(String genero);
	
    Iterable<Movie> findTop10ByOrderByYearDesc();


    Iterable<Movie> findByYearOrderByName(String year);

    Iterable<Movie> findByName(String name);

}
