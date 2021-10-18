package com.muchachos.cinemaxx.Booking.Entity;

import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = false)
    @JoinColumn(name="seat_id")
    private List <Seat> seats = new ArrayList<>();

    @ManyToOne
    Screening screening;
}
