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
    Integer id;

    @Column(length = 200,nullable = false)
    String title;

    @Column(length = 20,nullable = false)
    String rating;

    public Movie(Integer id, String title, String rating, String cast, String info, int duration, String trailer, String poster, String image) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.cast = cast;
        this.info = info;
        this.duration = duration;
    }

    @Column(length = 1024,nullable = false)
    String cast;

    @Column(length = 1024,nullable = false)
    String info;

    @Column(nullable = false)
    int duration;

    @Column(nullable = false)
    String trailer;

    @Column(nullable = false)
    String posters;

    @Column(nullable = false)
    String  image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private List<Screening> screenings = new ArrayList<>();
}
