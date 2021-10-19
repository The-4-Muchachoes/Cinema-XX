package com.muchachos.cinemaxx.Configuration;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Security.User.Role;
import com.muchachos.cinemaxx.Security.User.User;
import com.muchachos.cinemaxx.Security.User.UserRepo;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class DataSetup implements CommandLineRunner {

    MovieRepo movieRepo;
    TheaterRepo theaterRepo;
    ScreeningRepo screeningRepo;
    CinemaRepo cinemaRepo;
    UserRepo userRepo;

    public DataSetup(MovieRepo movieRepo,TheaterRepo theaterRepo,ScreeningRepo screeningRepo,CinemaRepo cinemaRepo, UserRepo userRepo){
        this.movieRepo=movieRepo;
        this.theaterRepo=theaterRepo;
        this.screeningRepo=screeningRepo;
        this.cinemaRepo=cinemaRepo;
        this.userRepo=userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Cinema cinema1 = cinemaRepo.save(new Cinema(null, "Elton Jones"));
        Cinema cinema2 = cinemaRepo.save(new Cinema(null, "Big Joe"));

        Theater theater1=theaterRepo.save(new Theater(null, "Marriot", cinema1));
        Theater theater2=theaterRepo.save(new Theater(null,"Red", cinema2));

        Movie movie1 = movieRepo.save(new Movie(null,"Ironman","12+","Brand Pit","Very good",95));
        Movie movie2 = movieRepo.save(new Movie(null,"Spiderman","12+","Cameron Diaz","Amazing",98));

        Screening screening1=screeningRepo.save(new Screening(null, LocalDateTime.now(), movie1, theater1));
        Screening screening2=screeningRepo.save(new Screening(null, LocalDateTime.now(), movie2, theater2));



        Role role1 =new Role(Role.Admin);
        Role role2 =new Role(Role.Super_Admin);

        User user1 =new User("Alex", "password");
        user1.addAuthority(role1);
        userRepo.save(user1);

        User user2 = new User("Peri", "password");
        user2.addAuthority(role2);
        userRepo.save(user2);
    }
}
