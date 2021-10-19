package com.muchachos.cinemaxx.Seat.Controller;

import com.muchachos.cinemaxx.Seat.DTO.EditSeatsRequest;
import com.muchachos.cinemaxx.Seat.SeatService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PutMapping(path = "user/seats")
    private ResponseEntity<?> changeAllSeatStatusById(EditSeatsRequest dto) {
        return seatService.changeSeatStatus(seatService.getAllSeatsById(dto.getSeatIds()), dto.getStatus());
    }

}
