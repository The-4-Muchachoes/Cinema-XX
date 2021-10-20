package com.muchachos.cinemaxx.Configuration;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Repo.ScreeningRepo;
import com.muchachos.cinemaxx.Security.User.Entity.Role;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import com.muchachos.cinemaxx.Security.User.Repo.UserRepo;
import com.muchachos.cinemaxx.Screening.DTO.CreateScreeningRequest;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningView;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataSetup implements CommandLineRunner {

    MovieRepo movieRepo;
    TheaterRepo theaterRepo;
    ScreeningServiceImpl screeningService;
    CinemaRepo cinemaRepo;
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper = new ModelMapper();

    public DataSetup(MovieRepo movieRepo,TheaterRepo theaterRepo,ScreeningServiceImpl screeningService,CinemaRepo cinemaRepo){
    public DataSetup(MovieRepo movieRepo,TheaterRepo theaterRepo,ScreeningRepo screeningRepo,CinemaRepo cinemaRepo, UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.movieRepo=movieRepo;
        this.theaterRepo=theaterRepo;
        this.screeningService=screeningService;
        this.cinemaRepo=cinemaRepo;
        this.userRepo=userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Cinema cinema1 = cinemaRepo.save(new Cinema(null, "Elton Jones"));
        Cinema cinema2 = cinemaRepo.save(new Cinema(null, "Big Joe"));

        Theater theater1=theaterRepo.save(new Theater(null, "Marriot",10,10 ,cinema1));
        Theater theater2=theaterRepo.save(new Theater(null,"Red",12,12, cinema2));

        Movie movie1 = movieRepo.save(new Movie(null,"Ironman","12+","Brand Pit","Very good",95));
        Movie movie2 = movieRepo.save(new Movie(null,"Spiderman","12+","Cameron Diaz","Amazing",98));

        CreateScreeningRequest screening1 = new CreateScreeningRequest(movie1.getId(), theater1.getId(), LocalDateTime.now());
        CreateScreeningRequest screening2 = new CreateScreeningRequest(movie2.getId(), theater2.getId(), LocalDateTime.now());

        screeningService.addScreening(screening1);
        screeningService.addScreening(screening2);
        Screening screening1=screeningRepo.save(new Screening(null, LocalDateTime.now(), movie1, theater1));
        Screening screening2=screeningRepo.save(new Screening(null, LocalDateTime.now(), movie2, theater2));



        Role role1 =new Role(Role.Admin);
        Role role2 =new Role(Role.Super_Admin);

        User user1 =new User("Alex", "password");
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        user1.addAuthority(role1);
        userRepo.save(user1);

        User user2 = new User("Peri", "password");
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
        user2.addAuthority(role2);
        userRepo.save(user2);
    }
}
