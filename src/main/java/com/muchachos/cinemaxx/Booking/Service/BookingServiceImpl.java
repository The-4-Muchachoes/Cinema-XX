package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingDTO;
import com.muchachos.cinemaxx.Booking.DTO.BookingScreeningDTO;
import com.muchachos.cinemaxx.Booking.DTO.NewBookingDTO;
import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Booking.Repo.BookingRepo;
import com.muchachos.cinemaxx.Exceptions.SeatAlreadyBookedException;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Seat.Repo.SeatRepo;
import com.muchachos.cinemaxx.Seat.SeatService;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<BookingDTO> createBooking(NewBookingDTO dto) {

        Booking booking = modelMapper.map(dto, Booking.class);
        booking.setSeats(seatRepo.findAllById(dto.getSeatIds()));
        booking.setScreening(screeningRepo.getById(dto.getScreeningId()));

        for (Seat seat : booking.getSeats())
            if (seat.isBooked()) throwSeatAlreadyBookedException();

        List<Seat> seats = booking.getSeats();
        seatService.changeSeatStatus(seats, true);

        BookingDTO bookingDTO = modelMapper.map(bookingRepo.save(booking), BookingDTO.class);
        bookingDTO.setScreening(new BookingScreeningDTO());

        bookingDTO.getScreening().setMovie(booking.getScreening().getMovie().getTitle());
        bookingDTO.getScreening().setTheater(booking.getScreening().getTheater().getName());

        return ResponseEntity.ok(bookingDTO);
    }

    private void throwSeatAlreadyBookedException() {
        throw new SeatAlreadyBookedException(null);
    }
}
