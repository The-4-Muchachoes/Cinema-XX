package com.muchachos.cinemaxx.Configuration;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSetup implements CommandLineRunner {

    MovieRepo movieRepo;
    TheaterRepo theaterRepo;
    ScreeningServiceImpl screeningService;
    CinemaRepo cinemaRepo;
    ModelMapper modelMapper = new ModelMapper();

    public DataSetup(MovieRepo movieRepo,TheaterRepo theaterRepo,ScreeningServiceImpl screeningService,CinemaRepo cinemaRepo){
        this.movieRepo=movieRepo;
        this.theaterRepo=theaterRepo;
        this.screeningService=screeningService;
        this.cinemaRepo=cinemaRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Cinema cinema1 = cinemaRepo.save(new Cinema(null, "Elton Jones"));
        Cinema cinema2 = cinemaRepo.save(new Cinema(null, "Big Joe"));

        Theater theater1=theaterRepo.save(new Theater(null, "Marriot",10,10 ,cinema1));
        Theater theater2=theaterRepo.save(new Theater(null,"Red",12,12, cinema2));

        Movie movie1 = movieRepo.save(new Movie(null,"Ironman","12+","Brand Pit","Very good",95));
        Movie movie2 = movieRepo.save(new Movie(null,"Spiderman","12+","Cameron Diaz","Amazing",98));

        ScreeningDTO screening1 = new ScreeningDTO(null, movie1.getId(), theater1.getId(), LocalDateTime.now());
        ScreeningDTO screening2 = new ScreeningDTO(null, movie2.getId(), theater2.getId(), LocalDateTime.now());

        screeningService.addScreening(screening1);
        screeningService.addScreening(screening2);
    }
}
