package com.killerexpertise.movies.rating.example.controller;

import com.killerexpertise.movies.rating.example.dto.RatingRequest;
import com.killerexpertise.movies.rating.example.model.Rating;
import com.killerexpertise.movies.rating.example.responce.ApiResponse;
import com.killerexpertise.movies.rating.example.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/{movieId}")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/ratings")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Rating>> createRating(
            @PathVariable Long movieId,
            @Valid @RequestBody RatingRequest request) {

        Rating rating = ratingService.rate(movieId, request.getUserId(), request.getScore());
        return ResponseEntity.status(201).body(new ApiResponse<>("Rating submitted successfully", rating));
    }

    @GetMapping("/ratings/get-all")
    public ResponseEntity<ApiResponse<List<Rating>>> listRatings(@PathVariable Long movieId) {
        List<Rating> ratings = ratingService.list(movieId);
        return ResponseEntity.ok(new ApiResponse<>("Ratings retrieved successfully", ratings));
    }
}
