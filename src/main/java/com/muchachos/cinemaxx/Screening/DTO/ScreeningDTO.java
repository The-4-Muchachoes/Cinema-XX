package com.muchachos.cinemaxx.Screening.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.muchachos.cinemaxx.Movie.DTO.MovieDTO;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Theater.DTO.TheaterDTO;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningDTO {

    Integer id;
    Integer movieId;
    @JsonIgnoreProperties("screenings")
    MovieDTO movie;
    Integer theaterId;
    @JsonIgnoreProperties("screenings")
    TheaterDTO theater;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    LocalDateTime startTime;
}
