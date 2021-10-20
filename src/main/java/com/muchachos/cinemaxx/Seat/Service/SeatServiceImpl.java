package com.muchachos.cinemaxx.Seat.Service;

import com.muchachos.cinemaxx.Exceptions.EntityNotFoundException;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Seat.Repo.SeatRepo;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    SeatRepo seatRepo;
    ScreeningRepo screeningRepo;
    TheaterRepo theaterRepo;

    public SeatServiceImpl(SeatRepo seatRepo, ScreeningRepo screeningRepo, TheaterRepo theaterRepo) {
        this.seatRepo = seatRepo;
        this.screeningRepo = screeningRepo;
        this.theaterRepo = theaterRepo;
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

    @Override
    public Iterable<Seat> getSeatsByScreeningId(int screeningId) {
        if (!screeningRepo.existsById(screeningId)) throw new EntityNotFoundException("No screening exists by that ID");
        return seatRepo.getAllByScreening_IdOrderByRowAscSeatNoAsc(screeningId);
    }

    @Override
    public Iterable<Seat> generateSeatsForScreening(Screening screening) {

        List<Seat> seats = new ArrayList<>();
        for (int row = 1 ; row <= screening.getTheater().getRows() ; row++) {
            for (int seat = 1 ; seat <= screening.getTheater().getSeats() ; seat++) {
                seats.add(new Seat(null, row, seat, Seat.Status.FREE, screening));
            }
        }
        return seatRepo.saveAll(seats);
    }
}
