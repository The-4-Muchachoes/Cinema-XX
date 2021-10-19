package com.muchachos.cinemaxx.Seat;

import com.muchachos.cinemaxx.Seat.Entity.Seat;
import org.springframework.http.ResponseEntity;

public interface SeatService {

    ResponseEntity<?> changeSeatStatus(Iterable<Seat> seats, Seat.Status status);

    Iterable<Seat> getAllSeatsById(Iterable<Integer> ids);
}
