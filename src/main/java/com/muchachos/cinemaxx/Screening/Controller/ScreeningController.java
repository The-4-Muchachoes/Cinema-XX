package com.muchachos.cinemaxx.Screening.Controller;

import com.muchachos.cinemaxx.Screening.DTO.CreateScreeningRequest;
import com.muchachos.cinemaxx.Screening.DTO.EditScreeningRequest;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningView;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ScreeningController {

    @Autowired
    ScreeningServiceImpl screeningService;

    @GetMapping(path = "/{cinemaId}/{date}")
    private Iterable<ScreeningView> getScreeningsByCinemaAndDate(@PathVariable int cinemaId, @PathVariable String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d = LocalDate.parse(date, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinemaId, d, null);
    }

    @GetMapping(path = "/{cinema_id}/{startDate}/{endDate}")
    private Iterable<ScreeningView> getScreeningsByCinemaBetweenDates(@PathVariable int cinema_id,
                                                                      @PathVariable String startDate,
                                                                      @PathVariable String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d1 = LocalDate.parse(startDate, formatter);
        LocalDate d2 = LocalDate.parse(endDate, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinema_id, d1, d2);
    }

    @PostMapping
    private ResponseEntity<?> addScreening(CreateScreeningRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(screeningService.addScreening(dto));
    }

    @PutMapping()
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    private ResponseEntity<?> editScreeningTime(EditScreeningRequest dto){
        return screeningService.editScreeningStartTime(dto);
    }

    @DeleteMapping(path = "/api/client_Admin/screenings/{id}")
    private ResponseEntity<?> deleteScreening(@PathVariable int id) {
        return screeningService.deleteScreening(id);
    }
}
