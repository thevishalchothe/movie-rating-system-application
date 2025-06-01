package com.killerexpertise.movies.rating.example.service.impl;

import com.killerexpertise.movies.rating.example.exception.MovieNotFoundException;
import com.killerexpertise.movies.rating.example.model.Movie;
import com.killerexpertise.movies.rating.example.model.Rating;
import com.killerexpertise.movies.rating.example.repository.MovieRepository;
import com.killerexpertise.movies.rating.example.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> listAll() {
        logger.info("Fetching all movies from the repository");
        List<Movie> movies = movieRepository.findAll();
        logger.debug("Total movies found: {}", movies.size());
        return movies;
    }

    @Override
    public Movie create(Movie movie) {
        logger.info("Saving new movie: {}", movie.getTitle());
        Movie saved = movieRepository.save(movie);
        logger.debug("Movie saved with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public Movie get(Long id) {
        logger.info("Fetching movie with ID: {}", id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Movie with ID {} not found", id);
                    return new MovieNotFoundException(id);
                });
        logger.debug("Movie found: {}", movie.getTitle());
        return movie;
    }

    @Override
    public Double getAvgRating(Movie movie) {
        logger.info("Calculating average rating for movie: {}", movie.getTitle());
        double avg = movie.getRatings()
                .stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);
        logger.debug("Average rating calculated: {}", avg);
        return avg;
    }
}
