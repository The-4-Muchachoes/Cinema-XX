package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Screening.DTO.CreateScreeningRequest;
import com.muchachos.cinemaxx.Screening.DTO.EditScreeningRequest;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningView;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningService {

    List<ScreeningView> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate startDate, LocalDate endDate);
    ScreeningView addScreening(CreateScreeningRequest dto);
    ResponseEntity<?> editScreeningStartTime(EditScreeningRequest dto);
    ResponseEntity<?> deleteScreening(int id);
}
