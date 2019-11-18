package com.fiap.netflix.movie.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.netflix.movie.domain.Movies;

@Entity
@Table()
public class FavoriteMovie {
	
	private int id;
    private Movies movies;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteMovie that = (FavoriteMovie) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "movie", referencedColumnName = "id", nullable = false)
    @JsonProperty(value = "movie")
    public Movies getMovie() {
        return movies;
    }

    public void setMovie(Movies movieByMovies) {
        this.movies = movieByMovies;
    }
}
