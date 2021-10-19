package com.muchachos.cinemaxx.Screening.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.muchachos.cinemaxx.Movie.DTO.MovieDTO;
import com.muchachos.cinemaxx.Theater.DTO.TheaterDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningView {

    Integer id;
    @JsonIgnoreProperties("screenings")
    MovieDTO movie;
    @JsonIgnoreProperties("screenings")
    TheaterDTO theater;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    LocalDateTime startTime;
}
