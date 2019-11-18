package com.fiap.netflix.movie.models;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Movie {
	
	private int id;
    private String name;
    private String descricao;
    private String year;
    private Collection<FavoriteMovie> favoriteMovies;
    private Genero genero;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return descricao;
    }

    public void setDescription(String description) {
        this.descricao = description;
    }


    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name) &&
                Objects.equals(descricao, movie.descricao) &&
                Objects.equals(year, movie.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, descricao, year);
    }

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    public Collection<FavoriteMovie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Collection<FavoriteMovie> favoriteMoviesById) {
        this.favoriteMovies = favoriteMoviesById;
    }

    @ManyToOne
    @JoinColumn(name = "genero", referencedColumnName = "id", nullable = false)
    @JsonProperty(value = "genero")
    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genderByGender) {
        this.genero = genderByGender;
    }



}
