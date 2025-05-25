package com.killerexpertise.movies.rating.example.service.impl;

import com.killerexpertise.movies.rating.example.model.Movie;
import com.killerexpertise.movies.rating.example.model.Rating;
import com.killerexpertise.movies.rating.example.repository.MovieRepository;
import com.killerexpertise.movies.rating.example.repository.RatingRepository;
import com.killerexpertise.movies.rating.example.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository repo;

    @Autowired
    private MovieRepository movieRepo;

    @Override
    public Rating rate(Long movieId, Integer userId, Integer score) {
        if (score < 1 || score > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10");
        }

        Movie movie = movieRepo.findById(movieId).orElseThrow();
        Optional<Rating> existing = repo.findByUserIdAndMovieId(userId, movieId);

        Rating rating = existing.orElse(new Rating());
        rating.setUserId(userId);
        rating.setRating(score);
        rating.setMovie(movie);

        return repo.save(rating);
    }

    @Override
    public List<Rating> list(Long movieId) {
        return repo.findAllByMovieId(movieId);
    }
}
