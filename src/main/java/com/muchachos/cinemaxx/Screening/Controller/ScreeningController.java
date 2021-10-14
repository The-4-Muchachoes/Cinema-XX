package com.muchachos.cinemaxx.Screening.Controller;

import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping(path = "api/screenings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScreeningController {

    @Autowired
    ScreeningServiceImpl screeningService;

    @GetMapping
    private Iterable<ScreeningDTO> getScreeningsByCinemaAndDate(@RequestParam int cinemaId, @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d = LocalDate.parse(date, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinemaId, d);


    }
    @PostMapping
    private ScreeningDTO addScreening(@RequestParam int movie_id, @RequestParam  int theater_id,@RequestParam String date, @RequestParam String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyyThh:mm");
        LocalDateTime d = LocalDateTime.parse(date+"T" + time, formatter);
        return screeningService.addScreening(movie_id,theater_id,d);

    }
    private Iterable<ScreeningDTO>editScreening(@RequestParam int movie_id, @RequestParam int theater_id, @RequestParam String date, @RequestParam String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyyTh:mm");
        LocalDateTime e = LocalDateTime.parse(date+"T" + time, formatter);
        return null;

    }

}
