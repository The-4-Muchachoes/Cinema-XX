package com.muchachos.cinemaxx.Screening.Entity;

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

    public Screening(Integer id, LocalDateTime startTime) {
        this.id = id;
        StartTime = startTime;
    }

    @Column
    LocalDateTime StartTime;

    @ManyToOne
    Movie movie;

    @ManyToOne
    Theater theater;

}
