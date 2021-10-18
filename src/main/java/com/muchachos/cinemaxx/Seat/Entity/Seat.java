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
    public Seat(Integer id, int row, int seatNo, boolean booked, Screening screening) {
        this.id = id;
        this.row = row;
        this.seatNo = seatNo;
        this.booked = booked;
        this.screening = screening;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 3,nullable = false)
    int row;

    @Column(length = 3,nullable = false)
    int seatNo;

    @Column(length=23,nullable = false)
    boolean booked;

    @ManyToOne
    Booking booking;

    @ManyToOne
    Screening screening;
}
