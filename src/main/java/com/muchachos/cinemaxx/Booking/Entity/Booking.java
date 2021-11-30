package com.muchachos.cinemaxx.Booking.Entity;

import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import com.muchachos.cinemaxx.Security.User.Entity.User;
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
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="booking_id")
    private List <Seat> seats = new ArrayList<>();

    @ManyToOne
    private Screening screening;

    @ManyToOne
    private User user;

    private Status status;

    public enum Status { CONFIRMED, CANCELLED }
}

