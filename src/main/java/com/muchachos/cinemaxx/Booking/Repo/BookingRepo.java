package com.muchachos.cinemaxx.Booking.Repo;

import com.muchachos.cinemaxx.Booking.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Integer> {

    public List<Booking> findAllByUserId(int id);
}
