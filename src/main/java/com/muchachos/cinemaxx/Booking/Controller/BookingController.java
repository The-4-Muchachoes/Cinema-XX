package com.muchachos.cinemaxx.Booking.Controller;

import com.muchachos.cinemaxx.Booking.DTO.BookingDTO;
import com.muchachos.cinemaxx.Booking.Service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

    @Autowired
    BookingServiceImpl bookingService;

    @PostMapping(path="client/booking")
    private BookingDTO createBooking(BookingDTO bookingDTO){
        return bookingService.createBooking(bookingDTO);
    }

}
