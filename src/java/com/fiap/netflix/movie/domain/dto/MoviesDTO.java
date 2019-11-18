package com.fiap.netflix.movie.domain.dto;

import org.modelmapper.ModelMapper;

import com.fiap.netflix.movie.domain.Movies;


public class MoviesDTO {

	private Long id;
	private String nome;
	private Long ano;
	private String genero;

	public MoviesDTO(Movies m) {
		this.id = m.getId();
		this.nome = m.getNome();
		this.ano = m.getAno();
		this.genero = m.getGenero();
	}

	public static MoviesDTO create(Movies m) {

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(m, MoviesDTO.class);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

}

