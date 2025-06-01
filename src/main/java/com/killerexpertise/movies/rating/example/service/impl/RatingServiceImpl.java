package com.killerexpertise.movies.rating.example.service.impl;

import com.killerexpertise.movies.rating.example.model.Movie;
import com.killerexpertise.movies.rating.example.model.Rating;
import com.killerexpertise.movies.rating.example.repository.MovieRepository;
import com.killerexpertise.movies.rating.example.repository.RatingRepository;
import com.killerexpertise.movies.rating.example.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);

    @Autowired
    private RatingRepository repo;

    @Autowired
    private MovieRepository movieRepo;

    @Override
    public Rating rate(Long movieId, Integer userId, Integer score) {
        logger.info("User {} rating movie {} with score {}", userId, movieId, score);

        if (score < 1 || score > 10) {
            logger.warn("Invalid rating score: {}", score);
            throw new IllegalArgumentException("Rating must be between 1 and 10");
        }

        Movie movie = movieRepo.findById(movieId).orElseThrow(() -> {
            logger.error("Movie with ID {} not found", movieId);
            return new IllegalArgumentException("Movie not found");
        });

        Optional<Rating> existing = repo.findByUserIdAndMovieId(userId, movieId);

        Rating rating = existing.orElse(new Rating());
        rating.setUserId(userId);
        rating.setRating(score);
        rating.setMovie(movie);

        Rating saved = repo.save(rating);
        logger.debug("Rating saved: ID={}, Score={}", saved.getId(), saved.getRating());

        return saved;
    }

    @Override
    public List<Rating> list(Long movieId) {
        logger.info("Fetching all ratings for movie ID {}", movieId);
        List<Rating> ratings = repo.findAllByMovieId(movieId);
        logger.debug("Total ratings found: {}", ratings.size());
        return ratings;
    }
}
