package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingDTO;
import com.muchachos.cinemaxx.Booking.DTO.NewBookingDTO;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    ResponseEntity<BookingDTO> createBooking(NewBookingDTO dto);

}
