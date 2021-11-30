package com.muchachos.cinemaxx.Booking.Controller;

import com.muchachos.cinemaxx.Booking.DTO.CreateBookingRequest;
import com.muchachos.cinemaxx.Booking.Service.BookingService;
import com.muchachos.cinemaxx.Booking.Service.BookingServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(path="user/bookings")
    private ResponseEntity<?> createBooking(CreateBookingRequest dto){
        return bookingService.createBooking(dto);
    }

    @PutMapping(path = "user/bookings/{id}")
    private ResponseEntity<?> cancelBooking(@PathVariable int id) {
        return bookingService.cancelBooking(id);
    }
}
