package com.muchachos.cinemaxx.Configuration;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSetup implements CommandLineRunner {

    MovieRepo movieRepo;
    TheaterRepo theaterRepo;
    ScreeningRepo screeningRepo;
    CinemaRepo cinemaRepo;

    public DataSetup(MovieRepo movieRepo,TheaterRepo theaterRepo,ScreeningRepo screeningRepo,CinemaRepo cinemaRepo){
        this.movieRepo=movieRepo;
        this.theaterRepo=theaterRepo;
        this.screeningRepo=screeningRepo;
        this.cinemaRepo=cinemaRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Cinema cinema1 = cinemaRepo.save(new Cinema("Elton Jones"));
        cinemaRepo.save(new Cinema("Big Joe"));

        Theater theater1=theaterRepo.save(new Theater(null,"Marriot"));
        theaterRepo.save(new Theater(null,"Red"));

        Movie movie1=movieRepo.save(new Movie
                (null,"Ironman","12+","Brand Pit","Very good",95));
        movieRepo.save(new Movie(null,"Spiderman","12+","Cameron Diaz","Amazing",98));

        Screening screening1=screeningRepo.save(new Screening(null, LocalDateTime.now()));
        screeningRepo.save(new Screening(null,LocalDateTime.now()));
    }
}
