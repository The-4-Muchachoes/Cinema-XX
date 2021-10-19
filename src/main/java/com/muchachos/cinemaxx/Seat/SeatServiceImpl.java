package com.muchachos.cinemaxx.Seat;

import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Seat.Repo.SeatRepo;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

    SeatRepo seatRepo;

    public SeatServiceImpl(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    @Override
    public Iterable<Seat> changeSeatStatus(Iterable<Seat> seats, boolean booked) {
        seats.forEach(seat -> seat.setBooked(booked));
        return seatRepo.saveAll(seats);
    }
}
