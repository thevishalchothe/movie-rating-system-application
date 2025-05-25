package com.killerexpertise.movies.rating.example.repository;

import com.killerexpertise.movies.rating.example.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

