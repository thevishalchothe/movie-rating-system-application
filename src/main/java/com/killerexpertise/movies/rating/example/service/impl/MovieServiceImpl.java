package com.killerexpertise.movies.rating.example.service.impl;

import com.killerexpertise.movies.rating.example.exception.MovieNotFoundException;
import com.killerexpertise.movies.rating.example.model.Movie;
import com.killerexpertise.movies.rating.example.model.Rating;
import com.killerexpertise.movies.rating.example.repository.MovieRepository;
import com.killerexpertise.movies.rating.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }


    @Override
    public Double getAvgRating(Movie movie) {
        return movie.getRatings()
                    .stream()
                    .mapToInt(Rating::getRating)
                    .average()
                    .orElse(0.0);
    }
}
