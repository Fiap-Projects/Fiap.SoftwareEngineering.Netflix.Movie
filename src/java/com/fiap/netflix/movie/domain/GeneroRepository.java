package com.fiap.netflix.movie.domain;

import org.springframework.data.repository.CrudRepository;

import com.fiap.netflix.movie.models.Genero;

public interface GeneroRepository extends CrudRepository<Genero, Long> {

    Iterable<Genero> findAllByDescription(String description);
}
