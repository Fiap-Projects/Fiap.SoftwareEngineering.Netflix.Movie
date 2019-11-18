package com.fiap.netflix.movie.domain;

import org.springframework.data.repository.CrudRepository;

import com.fiap.netflix.movie.models.ProfileMovie;

public interface ProfileMovieRepository extends CrudRepository<ProfileMovie, Long> {
}
