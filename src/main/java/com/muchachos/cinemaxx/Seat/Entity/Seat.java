package com.muchachos.cinemaxx.Seat.Entity;

import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 3,nullable = false)
    int x;

    @Column(length = 3,nullable = false)
    int y;

    @Column(length=23,nullable = false)
    boolean booked;

    @ManyToOne
    Booking booking;

    @ManyToOne
    Screening screening;
}
