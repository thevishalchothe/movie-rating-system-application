package com.killerexpertise.movies.rating.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RatingRequest {
    @NotNull
    private Integer userId;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer score;
}
