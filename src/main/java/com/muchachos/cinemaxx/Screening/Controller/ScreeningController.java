package com.muchachos.cinemaxx.Screening.Controller;

import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path = "api/screenings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScreeningController {

    @Autowired
    ScreeningServiceImpl screeningService;

    @GetMapping
    private Iterable<ScreeningDTO> getScreeningsByCinemaAndDate(@RequestParam int cinemaId, @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d = LocalDate.parse(date, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinemaId, d, null);
    }

}
