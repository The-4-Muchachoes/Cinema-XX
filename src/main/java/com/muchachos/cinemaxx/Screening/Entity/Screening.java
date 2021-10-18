package com.muchachos.cinemaxx.Screening.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonIgnoreProperties("screenings")
    Movie movie;

    @ManyToOne
    @JsonIgnoreProperties("screenings")
    Theater theater;
}
