package com.fiap.netflix.movie.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MoviesService {
	
	@Autowired
	private MovieRepository rep;
	
	public Iterable<Movies> getMovies() {
		return rep.findAll();
	}
	
	public Optional<Movies> getMoviesById(Long id){
		return rep.findById(id);
		
	}

	public Iterable<Movies> getMoviesByGenero(String genero) {
		return rep.findAllByGenero(genero);
	}

	public Movies save(Movies movies) {
		return rep.save(movies);
		
	}

	public Movies update(Movies movies, Long id) {
		Assert.notNull(id, "Nao foi possivel atualizar o registro");
		
		Optional<Movies> optional = getMoviesById(id);
		if (optional.isPresent()) {
			Movies db = optional.get();
			db.setNome(movies.getNome());
			db.setGenero(movies.getGenero());
			db.setGenero(movies.getGenero());
			
			System.out.println("Filme id " + db.getId());
			
			rep.save(db);
			
			return db;
		}else {
			throw new RuntimeException("Nao foi possivel atualizar o registro");
		}
		
	}
}
