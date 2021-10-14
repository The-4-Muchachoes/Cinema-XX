package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTOWithTitleAndRating;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {

    List<ScreeningDTOWithTitleAndRating> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate startDate, LocalDate endDate);
    ScreeningDTOWithTitleAndRating addScreening(int movie_id, int theater_id, LocalDateTime startTime );
    ScreeningDTO editScreening(ScreeningDTO dto);
    ResponseEntity<?> deleteScreening(int id);
}
