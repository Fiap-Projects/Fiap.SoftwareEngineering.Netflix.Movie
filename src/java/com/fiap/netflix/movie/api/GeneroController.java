package com.fiap.netflix.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.netflix.movie.domain.GeneroRepository;
import com.fiap.netflix.movie.models.Genero;

@RestController
public class GeneroController {

	@Autowired
	private GeneroRepository generoRepository;

	@RequestMapping(value = "/gender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Genero> getGenders() {
		return this.generoRepository.findAll();
	}
}
