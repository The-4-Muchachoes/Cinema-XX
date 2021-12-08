package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingResponse;
import com.muchachos.cinemaxx.Booking.DTO.BookingView;
import com.muchachos.cinemaxx.Booking.DTO.CreateBookingRequest;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    ResponseEntity<List<BookingResponse>> getBookingsByUser(User user);
    ResponseEntity<BookingView> createBooking(User user, CreateBookingRequest dto);
    ResponseEntity<?> cancelBooking(User user, int id);

}
