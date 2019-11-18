package com.fiap.netflix.movie.domain;

import org.springframework.data.repository.CrudRepository;

import com.fiap.netflix.movie.models.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
