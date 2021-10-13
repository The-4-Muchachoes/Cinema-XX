package com.muchachos.cinemaxx.Movie.Entity;

import com.muchachos.cinemaxx.Screening.Entity.Screening;
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
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    @Column(length = 200,nullable = false)
    String title;

    @Column(length = 20,nullable = false)
    String rating;

    @Column(length = 1024,nullable = false)
    String cast;

    @Column(length = 1024,nullable = false)
    String info;

    @Column(nullable = false)
    int duration;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private List<Screening> screenings = new ArrayList<>();


}
