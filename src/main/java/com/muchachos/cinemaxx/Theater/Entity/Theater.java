package com.muchachos.cinemaxx.Theater.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="theater_name",length = 50, nullable = false)
    String name;

    @Column(name="theater_rows",nullable = false)
    int rows;

    @Column(nullable = false)
    int seats;

    @ManyToOne
    @JsonIgnoreProperties("theaters")
    Cinema cinema;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "theater_id")
    private List<Screening> screenings = new ArrayList<>();

    public Theater(Integer id, String name, int rows, int seats, Cinema cinema) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.seats = seats;
        this.cinema = cinema;
    }
}
