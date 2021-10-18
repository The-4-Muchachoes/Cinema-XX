package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingDTO;
import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Booking.Repo.BookingRepo;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Seat.DTO.SeatDTO;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Seat.Repo.SeatRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private ModelMapper modelMapper;


    private ScreeningRepo screeningRepo;

    private BookingRepo bookingRepo;

    private SeatRepo seatRepo;

    public BookingServiceImpl(BookingRepo bookingRepo,SeatRepo seatRepo, ScreeningRepo screeningRepo) {
        this.modelMapper =new ModelMapper();
        this.bookingRepo = bookingRepo;
        this.seatRepo = seatRepo;
        this.screeningRepo = screeningRepo;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {

        Booking booking = modelMapper.map(bookingDTO,Booking.class);

        booking.setSeats(seatRepo.findAllById(bookingDTO.getSeatIds()));

        booking.setScreening(screeningRepo.getById(bookingDTO.getScreeningId()));


        for (Seat seat: booking.getSeats()) {
            seat.setBooked(true);
            seatRepo.save(seat);
        }

        BookingDTO saved = modelMapper.map(bookingRepo.save(booking),BookingDTO.class);

        saved.setSeatIds(null);
        saved.setScreeningId(null);

        return saved;
    }
}
