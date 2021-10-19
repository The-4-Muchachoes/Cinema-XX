package com.muchachos.cinemaxx.Seat;

import com.muchachos.cinemaxx.Seat.Entity.Seat;

public interface SeatService {

    Iterable<Seat> changeSeatStatus(Iterable<Seat> seatIds, boolean booked);
}
