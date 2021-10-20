package com.muchachos.cinemaxx.Movie.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="movie")
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

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

    String trailerUrl;

    String posterUrl;

    String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties("movie")
    List<Screening> screenings = new ArrayList<>();

    public Movie(Integer id, String title, String rating, String cast, String info, int duration) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.cast = cast;
        this.info = info;
        this.duration = duration;
    }

    public Movie(Integer id, String title, String rating, String cast, String info,
                 int duration, String trailerUrl, String posterUrl, String imageUrl) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.cast = cast;
        this.info = info;
        this.duration = duration;
        this.trailerUrl = trailerUrl;
        this.posterUrl = posterUrl;
        this.imageUrl = imageUrl;
    }
}
