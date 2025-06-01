package com.killerexpertise.movies.rating.example.controller;

import com.killerexpertise.movies.rating.example.dto.RatingRequest;
import com.killerexpertise.movies.rating.example.model.Rating;
import com.killerexpertise.movies.rating.example.responce.ApiResponse;
import com.killerexpertise.movies.rating.example.service.RatingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/{movieId}")
public class RatingController {

    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    private RatingService ratingService;

    @PostMapping("/ratings")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Rating>> createRating(
            @PathVariable Long movieId,
            @Valid @RequestBody RatingRequest request) {

        logger.info("Received request to rate movieId={}, userId={}, score={}",
                movieId, request.getUserId(), request.getScore());

        Rating rating = ratingService.rate(movieId, request.getUserId(), request.getScore());

        logger.debug("Rating created: {}", rating);

        return ResponseEntity.status(201).body(new ApiResponse<>("Rating submitted successfully", rating));
    }

    @GetMapping("/ratings/get-all")
    public ResponseEntity<ApiResponse<List<Rating>>> listRatings(@PathVariable Long movieId) {

        logger.info("Fetching all ratings for movieId={}", movieId);

        List<Rating> ratings = ratingService.list(movieId);

        logger.debug("Ratings found: {}", ratings.size());

        return ResponseEntity.ok(new ApiResponse<>("Ratings retrieved successfully", ratings));
    }
}
