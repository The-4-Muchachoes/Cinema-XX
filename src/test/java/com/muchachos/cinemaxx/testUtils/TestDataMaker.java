package com.muchachos.cinemaxx.testUtils;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.CreateScreeningRequest;
import com.muchachos.cinemaxx.Screening.Service.ScreeningService;
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
                120,
                "https://youtu.be/8ugaeA-nMTc",
                "https://www.imdb.com/title/tt0371746/mediaviewer/rm1544850432/?ref_=tt_ov_i",
                "https://www.imdb.com/title/tt0371746/mediaviewer/rm320113664/?ref_=ext_shr_lnk")).getId());
        ids.add(movieRepo.save(new Movie(null,
                "Superman",
                "PG12",
                "Rick Mortyson, Lois Griffin",
                "Superman must save Lois",
                90,
                "https://www.youtube.com/watch?v=TYMMOjBUPMM",
                "https://m.imdb.com/title/tt0145487/mediaviewer/rm3632146944/?fbclid=IwAR1R_iqLbOlVBVZ0IosrxRXQ89blCJKmarwSInP7MZEzCWS-B6JXoM73rGc",
                "https://m.imdb.com/title/tt0145487/mediaviewer/rm1262040064/")).getId());

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
            ids.add(theaterRepo.save(new Theater(null, "A",10,10 ,cinema)).getId());
            ids.add(theaterRepo.save(new Theater(null, "B",12,12, cinema)).getId());
        }

        return ids;
    }

    public static List<Integer> makeScreenings(
            ScreeningService screeningService,
            TheaterRepo theaterRepo,
            MovieRepo movieRepo
    ) {

//        screeningRepo.deleteAll();
        List<Integer> ids = new ArrayList<>();

        for (Theater theater : theaterRepo.findAll()) {

            CreateScreeningRequest screening1 = new CreateScreeningRequest(movieRepo.getById(1).getId(), theater.getId(), LocalDateTime.now());
            CreateScreeningRequest screening2 = new CreateScreeningRequest(movieRepo.getById(2).getId(), theater.getId(), LocalDateTime.now());

            screeningService.addScreening(screening1);
            screeningService.addScreening(screening2);
        }

        return ids;
    }
}
