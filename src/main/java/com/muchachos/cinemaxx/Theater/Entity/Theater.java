package com.muchachos.cinemaxx.Theater.Entity;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 50,nullable = false)
    String theaterName;

    @ManyToOne
    Cinema cinema;
}
