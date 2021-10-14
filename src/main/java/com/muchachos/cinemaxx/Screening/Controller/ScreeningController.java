package com.muchachos.cinemaxx.Screening.Controller;

import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTOWithTitleAndRating;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path = "api/screenings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScreeningController {

    @Autowired
    ScreeningServiceImpl screeningService;

    @GetMapping
    private Iterable<ScreeningDTOWithTitleAndRating> getScreeningsByCinemaAndDate(@RequestParam int cinemaId, @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d = LocalDate.parse(date, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinemaId, d, null);
    }

    @GetMapping(path = "/{cinema_id}/between_dates")
    private Iterable<ScreeningDTOWithTitleAndRating> getScreeningsByCinemaBetweenDates(@PathVariable int cinema_id,
                                                                     @RequestParam String startDate,
                                                                     @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d1 = LocalDate.parse(startDate, formatter);
        LocalDate d2 = LocalDate.parse(endDate, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinema_id, d1, d2);

    }
    @PostMapping
    private ScreeningDTOWithTitleAndRating addScreening(@RequestParam int movie_id, @RequestParam  int theater_id, @RequestParam String date, @RequestParam String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyyThh:mm");
        LocalDateTime d = LocalDateTime.parse(date+"T" + time, formatter);
        return screeningService.addScreening(movie_id,theater_id,d);

    }

    @PutMapping
    private ScreeningDTO editScreening(ScreeningDTO dto){
        return screeningService.editScreening(dto);
    }


    @DeleteMapping(path = "{id}")
    private ResponseEntity<?> deleteScreening(@PathVariable int id) {
        return screeningService.deleteScreening(id);
    }
}
