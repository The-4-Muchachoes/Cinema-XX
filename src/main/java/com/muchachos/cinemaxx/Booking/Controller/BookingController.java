package com.muchachos.cinemaxx.Booking.Controller;

import com.muchachos.cinemaxx.Booking.DTO.CreateBookingRequest;
import com.muchachos.cinemaxx.Booking.Service.BookingService;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import com.muchachos.cinemaxx.Security.User.Service.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Booking")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @PostMapping(path="user/bookings")
    private ResponseEntity<?> createBooking(
            @RequestHeader("Authorization") String token,
            CreateBookingRequest dto){

        User user = userService.getAuthenticatedUser(token);

        return bookingService.createBooking(user, dto);
    }

    @PutMapping(path = "user/bookings/{id}")
    private ResponseEntity<?> cancelBooking(@PathVariable int id) {
        return bookingService.cancelBooking(id);
    }
}
