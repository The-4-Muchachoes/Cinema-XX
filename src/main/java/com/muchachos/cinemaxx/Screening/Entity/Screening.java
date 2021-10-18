package com.muchachos.cinemaxx.Screening.Entity;

import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    LocalDateTime startTime;

    @ManyToOne
    Movie movie;

    @ManyToOne
    Theater theater;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="seat_id")
    private List <Seat> seats = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="booking-id")
    private List <Booking> bookings = new ArrayList<>();





}
