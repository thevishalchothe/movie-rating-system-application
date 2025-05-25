package com.killerexpertise.movies.rating.example.repository;

import com.killerexpertise.movies.rating.example.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndMovieId(Integer userId, Long movieId);

    List<Rating> findAllByMovieId(Long movieId);
}
