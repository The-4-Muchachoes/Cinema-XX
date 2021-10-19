package com.muchachos.cinemaxx.Seat;

import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Seat.Repo.SeatRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

    SeatRepo seatRepo;

    public SeatServiceImpl(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    @Override
    public ResponseEntity<?> changeSeatStatus(Iterable<Seat> seats, Seat.Status status) {
        seats.forEach(seat -> seat.setStatus(status));
        if (status == Seat.Status.FREE) seats.forEach(seat -> seat.setBooking(null));
        seatRepo.saveAll(seats);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Iterable<Seat> getAllSeatsById(Iterable<Integer> ids) {
        return seatRepo.findAllById(ids);
    }
}
