package com.killerexpertise.movies.rating.example.service;

import com.killerexpertise.movies.rating.example.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    Movie create(Movie movie);
    Movie get(Long id);
    Double getAvgRating(Movie movie);
}
