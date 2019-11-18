package com.fiap.netflix.movie.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fiap.netflix.movie.domain.GeneroRepository;
import com.fiap.netflix.movie.domain.ProfileMovieRepository;
import com.fiap.netflix.movie.domain.ProfileRepository;
import com.fiap.netflix.movie.models.Genero;
import com.fiap.netflix.movie.models.Movie;
import com.fiap.netflix.movie.models.Profile;
import com.fiap.netflix.movie.models.ProfileMovie;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MoviesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ProfileMovieRepository profileMovieRepository;

	@Before
	@Rollback(true)
	public void before() {

		Profile profile = new Profile();
		profile.setName("profile test");
		this.profileRepository.save(profile);

		Genero genero = new Genero();
		genero.setDescription("gender for test in movies repository");
		genero.setId(1);
		this.generoRepository.save(genero);

		List<Movie> movieList = new ArrayList<>();
		List<ProfileMovie> listProfileMovie = new ArrayList<>();
		Movie movie;
		ProfileMovie profileMovie;
		Date date = new Date(23, 11, 2019);
		for (int i = 0; i < 10; i++) {
			movie = new Movie();
			movie.setName("movie #" + i);
			movie.setDescription("Description Movie #" + i);
			movie.setGenero(genero);
			movie.setYear("2018");
			movieList.add(movie);

			profileMovie = new ProfileMovie();
			profileMovie.setMovie(movie);
			profileMovie.setProfile(profile);
			profileMovie.setWatchDate(date);
			listProfileMovie.add(profileMovie);
		}
		this.profileMovieRepository.saveAll(listProfileMovie);
	}

	@Test
	public void testMovieRoute() throws Exception {
		this.mockMvc.perform(get("/movie").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(Matchers.greaterThanOrEqualTo(10)));
	}

	@Test
	public void testMovieLast10Route() throws Exception {
		this.mockMvc.perform(get("/movieLast10").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.length()").value(Matchers.equalTo(10)));
	}

	@Test
	public void testMovieByYearRoute() throws Exception {
		this.mockMvc.perform(get("/movieByYear").contentType(MediaType.APPLICATION_JSON_UTF8).param("year", "2018"))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(Matchers.greaterThanOrEqualTo(10)));
	}

	@Test
	public void testMovieByYearWithoutYearParamRoute() throws Exception {
		this.mockMvc.perform(get("/movieByYear").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest());
	}
}
