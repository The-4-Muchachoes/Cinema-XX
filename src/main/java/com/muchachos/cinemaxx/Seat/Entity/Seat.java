package com.muchachos.cinemaxx.Seat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="theater_row",length = 3,nullable = false)
    int row;

    @Column(length = 3,nullable = false)
    int seatNo;

    @Column(length=23,nullable = false)
    Status status;

    @ManyToOne
    Booking booking;

    @ManyToOne
    Screening screening;

    public Seat(Integer id, int row, int seatNo, Status status, Screening screening) {
        this.id = id;
        this.row = row;
        this.seatNo = seatNo;
        this.status = status;
        this.screening = screening;
    }

    public enum Status { FREE, BOOKED, RESERVED }
}
