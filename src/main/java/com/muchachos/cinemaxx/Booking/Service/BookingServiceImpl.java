package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingView;
import com.muchachos.cinemaxx.Booking.DTO.BookingScreeningDTO;
import com.muchachos.cinemaxx.Booking.DTO.CreateBookingRequest;
import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Booking.Repo.BookingRepo;
import com.muchachos.cinemaxx.Exceptions.EntityNotFoundException;
import com.muchachos.cinemaxx.Exceptions.SeatAlreadyBookedException;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Seat.Repo.SeatRepo;
import com.muchachos.cinemaxx.Seat.Service.SeatService;

import com.muchachos.cinemaxx.Security.User.DTO.UserResponse;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private ModelMapper modelMapper;

    private ScreeningRepo screeningRepo;

    private BookingRepo bookingRepo;

    private SeatRepo seatRepo;

    private SeatService seatService;

    public BookingServiceImpl(BookingRepo bookingRepo,SeatRepo seatRepo, ScreeningRepo screeningRepo, SeatService seatService) {
        this.modelMapper =new ModelMapper();
        this.bookingRepo = bookingRepo;
        this.seatRepo = seatRepo;
        this.screeningRepo = screeningRepo;
        this.seatService = seatService;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ResponseEntity<BookingView> createBooking(User user, CreateBookingRequest dto) {

        Booking booking = modelMapper.map(dto, Booking.class);
        booking.setUser(user);
        booking.setSeats(seatRepo.findAllById(dto.getSeatIds()));
        booking.setScreening(screeningRepo.getById(dto.getScreeningId()));
        booking.setStatus(Booking.Status.CONFIRMED);

        for (Seat seat : booking.getSeats())
            if (seat.getStatus() != Seat.Status.FREE) throwSeatAlreadyBookedException();

        List<Seat> seats = booking.getSeats();
        seatService.changeSeatStatus(seats, Seat.Status.BOOKED);

        BookingView bookingView = modelMapper.map(bookingRepo.save(booking), BookingView.class);
        bookingView.setScreening(new BookingScreeningDTO());
        bookingView.setUser(modelMapper.map(user, UserResponse.class));

        bookingView.getScreening().setMovie(booking.getScreening().getMovie().getTitle());
        bookingView.getScreening().setTheater(booking.getScreening().getTheater().getName());

        return ResponseEntity.ok(bookingView);
    }

    @Override
    public ResponseEntity<?> cancelBooking(User user, int id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No booking exists by that ID"));

        if (!booking.getUser().equals(user))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        booking.setStatus(Booking.Status.CANCELLED);

        seatService.changeSeatStatus(booking.getSeats(), Seat.Status.FREE);

        return ResponseEntity.noContent().build();
    }

    private void throwSeatAlreadyBookedException() {
        throw new SeatAlreadyBookedException(null);
    }
}
