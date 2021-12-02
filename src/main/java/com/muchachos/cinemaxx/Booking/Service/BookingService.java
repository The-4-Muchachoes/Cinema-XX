package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingView;
import com.muchachos.cinemaxx.Booking.DTO.CreateBookingRequest;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    ResponseEntity<BookingView> createBooking(User user, CreateBookingRequest dto);
    ResponseEntity<?> cancelBooking(int id);

}
