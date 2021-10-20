package com.muchachos.cinemaxx.Movie.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    int id;
    String title;
    String cast;
    String info;
    String rating;
    int duration;
    String trailerUrl;
    String posterUrl;
    String imageUrl;
}
