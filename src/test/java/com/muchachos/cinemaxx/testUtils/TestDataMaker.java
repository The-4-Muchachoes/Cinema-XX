package com.muchachos.cinemaxx.testUtils;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataMaker {

    public static List<Integer> makeMovies(MovieRepo movieRepo) {
//        movieRepo.deleteAll();
        List<Integer> ids = new ArrayList<>();

        ids.add(movieRepo.save(new Movie(null,
                "Batman",
                "PG18",
                "George Clooney, Arnold Schwarzenegger",
                "Batman has to save the day again",
                120)).getId());
        ids.add(movieRepo.save(new Movie(null,
                "Superman",
                "PG12",
                "Rick Mortyson, Lois Griffin",
                "Superman must save Lois",
                90)).getId());

        return ids;
    }

    public static List<Integer> makeCinemas(CinemaRepo cinemaRepo) {
//        cinemaRepo.deleteAll();
        List<Integer> ids = new ArrayList<>();

        ids.add(cinemaRepo.save(new Cinema(null, "Amazing Cinema")).getId());
        ids.add(cinemaRepo.save(new Cinema(null, "Chris' Living Room")).getId());

        return ids;
    }

    public static List<Integer> makeTheaters(TheaterRepo theaterRepo, CinemaRepo cinemaRepo) {
//        theaterRepo.deleteAll();
        List<Integer> ids = new ArrayList<>();

        for (Cinema cinema : cinemaRepo.findAll()) {
            ids.add(theaterRepo.save(new Theater(null, "A", cinema)).getId());
            ids.add(theaterRepo.save(new Theater(null, "B", cinema)).getId());
        }

        return ids;
    }

    public static List<Integer> makeScreenings(
            ScreeningRepo screeningRepo,
            TheaterRepo theaterRepo,
            MovieRepo movieRepo
    ) {

//        screeningRepo.deleteAll();
        List<Integer> ids = new ArrayList<>();

        for (Theater theater : theaterRepo.findAll()) {
            ids.add(screeningRepo.save(new Screening(null, LocalDateTime.now(), movieRepo.getById(0), theater)).getId());
            ids.add(screeningRepo.save(new Screening(null, LocalDateTime.now(), movieRepo.getById(1), theater)).getId());
        }

        return ids;
    }
}
