package com.muchachos.cinemaxx.Seat.Repo;

import com.muchachos.cinemaxx.Seat.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepo extends JpaRepository<Seat,Integer> {

    Iterable<Seat> getAllByScreening_IdOrderByRowAscSeatNoAsc(int screeningId);
}
