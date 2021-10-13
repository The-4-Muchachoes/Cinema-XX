package com.muchachos.cinemaxx.Movie.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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


}
