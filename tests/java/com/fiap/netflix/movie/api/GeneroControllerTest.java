package com.fiap.netflix.movie.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fiap.netflix.movie.domain.GeneroRepository;
import com.fiap.netflix.movie.models.Genero;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GeneroControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GeneroRepository genderRepository;

	@Before
	@Rollback(true)
	public void before() {
		List<Genero> listGender = new ArrayList<>();
		Genero gender;
		for (int i = 0; i < 10; i++) {
			gender = new Genero();
			gender.setDescription("this gender is a numer #" + i);
			listGender.add(gender);
		}
		this.genderRepository.saveAll(listGender);
	}

	@Test
	public void testGenderRoute() throws Exception {
		this.mockMvc.perform(get("/genero").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$").isArray());
	}
}
