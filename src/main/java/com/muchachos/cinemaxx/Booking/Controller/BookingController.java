package com.muchachos.cinemaxx.Booking.Controller;

import com.muchachos.cinemaxx.Booking.DTO.BookingDTO;
import com.muchachos.cinemaxx.Booking.DTO.NewBookingDTO;
import com.muchachos.cinemaxx.Booking.Service.BookingServiceImpl;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/api/",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @PostMapping(path="client/bookings")
    private ResponseEntity<?> createBooking(NewBookingDTO dto){
        return bookingService.createBooking(dto);
    }

}
