package com.muchachos.cinemaxx.Screening.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningDTOWithTitleAndRating {

    Integer id;
    LocalDateTime startTime;
    String title;
    String rating;
}
