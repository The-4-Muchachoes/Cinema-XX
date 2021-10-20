package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingView;
import com.muchachos.cinemaxx.Booking.DTO.CreateBookingRequest;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    ResponseEntity<BookingView> createBooking(CreateBookingRequest dto);
    ResponseEntity<?> cancelBooking(int id);

}
