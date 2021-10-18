package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingDTO;
import com.muchachos.cinemaxx.Booking.Entity.Booking;

public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO);

}
