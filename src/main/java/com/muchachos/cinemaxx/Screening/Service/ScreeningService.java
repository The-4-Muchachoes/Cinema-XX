package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningService {

    List<ScreeningDTO> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate startDate, LocalDate endDate);
    ScreeningDTO addScreening(ScreeningDTO dto);
    ScreeningDTO editScreeningStartTime(ScreeningDTO dto);
    ResponseEntity<?> deleteScreening(int id);
}
