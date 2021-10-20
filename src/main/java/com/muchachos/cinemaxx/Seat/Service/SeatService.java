package com.muchachos.cinemaxx.Seat.Service;

import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import org.springframework.http.ResponseEntity;

public interface SeatService {

    ResponseEntity<?> changeSeatStatus(Iterable<Seat> seats, Seat.Status status);

    Iterable<Seat> getAllSeatsById(Iterable<Integer> ids);

    Iterable<Seat> getSeatsByScreeningId(int screeningId);

    Iterable<Seat> generateSeatsForScreening(Screening screening);
}
