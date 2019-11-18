package com.fiap.netflix.movie.domain;

import org.springframework.data.repository.CrudRepository;

import com.fiap.netflix.movie.models.FavoriteMovie;
import com.fiap.netflix.movie.models.Movie;
import com.fiap.netflix.movie.models.Profile;

import javax.transaction.Transactional;

public interface FavoriteMovieRepository extends CrudRepository<FavoriteMovie, Long> {
    boolean existsByMovieAndProfile(Movie movieByMovie, Profile profileByProfile);

    @Transactional
    void deleteByProfileAndMovie(Profile profile, Movie movie);
}
