package com.muchachos.cinemaxx.Screening.Controller;

import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path = "api/screenings", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScreeningController {

    @Autowired
    ScreeningServiceImpl screeningService;

    @GetMapping(path = "/{cinemaId}/{date}")
    private Iterable<ScreeningDTO> getScreeningsByCinemaAndDate(@PathVariable int cinemaId, @PathVariable String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d = LocalDate.parse(date, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinemaId, d, null);
    }

    @GetMapping(path = "/{cinema_id}/{startDate}/{endDate}")
    private Iterable<ScreeningDTO> getScreeningsByCinemaBetweenDates(@PathVariable int cinema_id,
                                                                     @PathVariable String startDate,
                                                                     @PathVariable String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate d1 = LocalDate.parse(startDate, formatter);
        LocalDate d2 = LocalDate.parse(endDate, formatter);
        return screeningService.getTitleTimeAndRatingByCinemaAndDate(cinema_id, d1, d2);
    }

    @PostMapping
    private ScreeningDTO addScreening(ScreeningDTO dto) {
        return screeningService.addScreening(dto);
    }

    @PutMapping
    private ScreeningDTO editScreening(ScreeningDTO dto){
        return screeningService.editScreeningStartTime(dto);
    }

    @DeleteMapping(path = "{id}")
    private ResponseEntity<?> deleteScreening(@PathVariable int id) {
        return screeningService.deleteScreening(id);
    }
}
