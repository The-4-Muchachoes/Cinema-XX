package com.muchachos.cinemaxx.Seat.Controller;

import com.muchachos.cinemaxx.Seat.DTO.EditSeatsRequest;
import com.muchachos.cinemaxx.Seat.DTO.SeatResponse;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Seat.Service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatController {

    private final SeatService seatService;
    private final ModelMapper modelMapper;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
        modelMapper = new ModelMapper();
    }

    @GetMapping(path = "user/seats/{screening_id}")
    private ResponseEntity<?> getSeatingAvailability(@PathVariable int screening_id) {
        List<SeatResponse> seats = new ArrayList<>();
        for (Seat seat : seatService.getSeatsByScreeningId(screening_id))
                seats.add(modelMapper.map(seat, SeatResponse.class));
        return ResponseEntity.ok(seats);
    }

    @PutMapping(path = "user/seats")
    private ResponseEntity<?> changeAllSeatStatusById(EditSeatsRequest dto) {
        return seatService.changeSeatStatus(seatService.getAllSeatsById(dto.getSeatIds()), dto.getStatus());
    }

}
