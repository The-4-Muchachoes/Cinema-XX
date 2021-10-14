package com.muchachos.cinemaxx.Screening.Service;

import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningService {

    List<ScreeningDTO> getTitleTimeAndRatingByCinemaAndDate(int cinemaId, LocalDate date);
}
