package com.muchachos.cinemaxx.Booking.Controller;

import com.muchachos.cinemaxx.Booking.DTO.CreateBookingRequest;
import com.muchachos.cinemaxx.Booking.Service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @PostMapping(path="user/bookings")
    private ResponseEntity<?> createBooking(CreateBookingRequest dto){
        return bookingService.createBooking(dto);
    }

    @PutMapping(path = "user/bookings/{id}")
    private ResponseEntity<?> cancelBooking(@PathVariable int id) {
        return bookingService.cancelBooking(id);
    }
}
