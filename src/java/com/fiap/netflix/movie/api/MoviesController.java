package com.fiap.netflix.movie.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.netflix.movie.domain.Movies;
import com.fiap.netflix.movie.domain.MoviesService;

@RestController
@RequestMapping("/api/v1/movies")
public class MoviesController {
	
	@Autowired
	private MoviesService service;
	
	@GetMapping
	public Iterable<Movies> get() {
		return service.getMovies();
	}

	@GetMapping("/{id}")
	public Optional<Movies> get(@PathVariable("id") Long id) {
		return service.getMoviesById(id);
	}
	
	@GetMapping("/genero/{genero}")
	public Iterable<Movies> getMoviesByGenero(@PathVariable("genero") String genero) {
		return service.getMoviesByGenero(genero);
	}
	
	@PostMapping
	public String post (@RequestBody Movies movies) {
		Movies m = service.save(movies);
		
		return "Filme salvo com sucesso: " + m.getId();
		
	}
	
	@PutMapping
	public String put (@PathVariable("id") Long id, @RequestParam Movies movies) {
		
		Movies m = service.update(movies, id);
		
		return "Filme atualizado com sucesso: " + m.getId();
		
	}
}
