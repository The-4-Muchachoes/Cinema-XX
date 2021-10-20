package com.muchachos.cinemaxx.Screening.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="screening")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    LocalDateTime startTime;

    @ManyToOne
    @JsonIgnoreProperties("screenings")
    Movie movie;

    @ManyToOne
    @JsonIgnoreProperties("screenings")
    Theater theater;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="screening_id")
    private List<Seat> seats = new ArrayList<>();
}
