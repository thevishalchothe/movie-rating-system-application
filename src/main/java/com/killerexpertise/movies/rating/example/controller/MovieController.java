package com.killerexpertise.movies.rating.example.controller;

import com.killerexpertise.movies.rating.example.model.Movie;
import com.killerexpertise.movies.rating.example.responce.ApiResponse;
import com.killerexpertise.movies.rating.example.service.MovieService;
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
    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Movie>>> getAll() {
        List<Movie> movies = movieService.listAll();
        return ResponseEntity.ok(new ApiResponse<>("Movies retrieved successfully", movies));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Movie>> create(@RequestBody Movie movie) {
        Movie created = movieService.create(movie);
        return ResponseEntity.status(201).body(new ApiResponse<>("Movie created successfully", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> get(@PathVariable Long id) {
        Movie m = movieService.get(id);
        Map<String, Object> res = new HashMap<>();
        res.put("movie", m);
        res.put("averageRating", movieService.getAvgRating(m));
        res.put("ratings", m.getRatings());
        return ResponseEntity.ok(new ApiResponse<>("Movie details retrieved", res));
    }
}
