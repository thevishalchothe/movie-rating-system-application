package com.killerexpertise.movies.rating.example.controller;

import com.killerexpertise.movies.rating.example.model.Movie;
import com.killerexpertise.movies.rating.example.responce.ApiResponse;
import com.killerexpertise.movies.rating.example.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Movie>>> getAll() {
        logger.info("Fetching all movies");
        List<Movie> movies = movieService.listAll();
        logger.debug("Movies retrieved: count={}", movies.size());
        return ResponseEntity.ok(new ApiResponse<>("Movies retrieved successfully", movies));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Movie>> create(@RequestBody Movie movie) {
        logger.info("Creating movie: {}", movie.getTitle());
        Movie created = movieService.create(movie);
        logger.debug("Movie created with ID={}", created.getId());
        return ResponseEntity.status(201).body(new ApiResponse<>("Movie created successfully", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> get(@PathVariable Long id) {
        logger.info("Fetching movie details for ID={}", id);
        Movie movie = movieService.get(id);
        Map<String, Object> res = new HashMap<>();
        res.put("movie", movie);
        res.put("averageRating", movieService.getAvgRating(movie));
        res.put("ratings", movie.getRatings());
        logger.debug("Movie details fetched: {}", movie.getTitle());
        return ResponseEntity.ok(new ApiResponse<>("Movie details retrieved", res));
    }
}
