package com.muchachos.cinemaxx.Booking.Service;

import com.muchachos.cinemaxx.Booking.DTO.BookingDTO;
import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Booking.Repo.BookingRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    private ModelMapper modelMapper;


    @Autowired
    private BookingRepo bookingRepo;

    public BookingServiceImpl(BookingRepo bookingRepo) {
        this.modelMapper =new ModelMapper();
        this.bookingRepo = bookingRepo;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO,Booking.class);
        return modelMapper.map(bookingRepo.save(booking),BookingDTO.class);
    }
}
