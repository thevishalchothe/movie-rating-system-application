package com.killerexpertise.movies.rating.example.service;

import com.killerexpertise.movies.rating.example.model.Rating;

import java.util.List;

public interface RatingService {
    Rating rate(Long movieId, Integer userId, Integer score);
    List<Rating> list(Long movieId);
}
