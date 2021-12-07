package com.muchachos.cinemaxx.Configuration;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Service.ScreeningService;
import com.muchachos.cinemaxx.Security.User.Entity.Role;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import com.muchachos.cinemaxx.Security.User.Repo.UserRepo;
import com.muchachos.cinemaxx.Screening.DTO.CreateScreeningRequest;
import com.muchachos.cinemaxx.Screening.Service.ScreeningServiceImpl;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSetup implements CommandLineRunner {

    MovieRepo movieRepo;
    TheaterRepo theaterRepo;
    ScreeningService screeningService;
    CinemaRepo cinemaRepo;
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    public DataSetup(MovieRepo movieRepo, TheaterRepo theaterRepo, ScreeningServiceImpl screeningService,
                     CinemaRepo cinemaRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.movieRepo = movieRepo;
        this.theaterRepo = theaterRepo;
        this.screeningService = screeningService;
        this.cinemaRepo = cinemaRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Cinema cinema1 = cinemaRepo.save(new Cinema(null, "Elton Jones"));
        Cinema cinema2 = cinemaRepo.save(new Cinema(null, "Big Joe"));

        Theater theater1 = theaterRepo.save(new Theater(null, "Marriot", 10, 10, cinema1));
        Theater theater2 = theaterRepo.save(new Theater(null, "Red", 10, 10, cinema1));
        Theater theater3 = theaterRepo.save(new Theater(null, "Hilton", 10, 10, cinema1));
        Theater theater4 = theaterRepo.save(new Theater(null, "Master", 10, 10, cinema1));
        Theater theater5 = theaterRepo.save(new Theater(null, "Penelope", 10, 10, cinema2));
        Theater theater6 = theaterRepo.save(new Theater(null, "Sparta", 10, 10, cinema2));
        Theater theater7 = theaterRepo.save(new Theater(null, "Junior", 10, 10, cinema2));
        Theater theater8 = theaterRepo.save(new Theater(null, "Senior", 10, 10, cinema2));

        Movie movie1 = movieRepo.save(new Movie(null, "Ironman", "12+", "Brand Pit", "Very good", 95, "https://youtu.be/8ugaeA-nMTc", "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_FMjpg_UY711_.jpg", "https://m.media-amazon.com/images/M/MV5BNzgwOWU2YTAtYWEwNy00NDBkLThkMjktYjNkOTZmOWIwYzg4XkEyXkFqcGdeQXVyNTU5MzI1OTM@._V1_FMjpg_UX600_.jpg"));
        Movie movie2 = movieRepo.save(new Movie(null, "Spiderman", "12+", "Cameron Diaz", "Amazing", 98, "https://www.youtube.com/watch?v=TYMMOjBUPMM", "https://m.media-amazon.com/images/M/MV5BZDEyN2NhMjgtMjdhNi00MmNlLWE5YTgtZGE4MzNjMTRlMGEwXkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_.jpg", "https://m.media-amazon.com/images/M/MV5BMjMxMDAwMjUxOV5BMl5BanBnXkFtZTgwMDI0ODkxMjI@._V1_FMjpg_UX1024_.jpg"));
        Movie movie3 = movieRepo.save(new Movie(null, "The Live of Others", "R", "Ulrich Muhe", "Thriller", 137, "https://www.youtube.com/watch?v=n3_iLOp6IhM", "https://m.media-amazon.com/images/M/MV5BOThkM2EzYmMtNDE3NS00NjlhLTg4YzktYTdhNzgyOWY3ZDYzXkEyXkFqcGdeQXVyNzQzNzQxNzI@._V1_FMjpg_UY679_.jpg", "https://m.media-amazon.com/images/M/MV5BMTE5MTYzMzg4OV5BMl5BanBnXkFtZTYwNjAyMTg2._V1_.jpg"));
        Movie movie4 = movieRepo.save(new Movie(null, "Inception", "PG-13", "Leonardo DiCaprio", "Thriller", 148, "https://www.youtube.com/watch?v=YoHD9XEInc0", "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg", "https://m.media-amazon.com/images/M/MV5BMTk1MzExMjM5Nl5BMl5BanBnXkFtZTcwNjI2NjQ0Mw@@._V1_.jpg"));
        Movie movie5 = movieRepo.save(new Movie(null, "Queen of Katwe", "PG", "Madina Nalwanga", "Inspiring", 124, "https://www.youtube.com/watch?v=z4l3-_yub5A", "https://m.media-amazon.com/images/M/MV5BNzQ0MDg2NTY4N15BMl5BanBnXkFtZTgwOTk2NzU3OTE@._V1_.jpg", "https://m.media-amazon.com/images/M/MV5BMjc2MmU4NDYtNDA5OS00ZDgxLWE1MjMtMjA2OGMyODczODA2XkEyXkFqcGdeQXVyNjAwNTYwNDg@._V1_.jpg"));
        Movie movie6 = movieRepo.save(new Movie(null, "A Separation", "PG-13", "Payman Maadi", "Genre Drama", 122, "https://www.youtube.com/watch?v=58Onuy5USTc", "https://m.media-amazon.com/images/M/MV5BN2JmMjViMjMtZTM5Mi00ZGZkLTk5YzctZDg5MjFjZDE4NjNkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg", "https://m.media-amazon.com/images/M/MV5BNzU4YWRkYTgtMzYzZS00ZjA3LWJiZDItOGI5MTM3MTE5MTBhXkEyXkFqcGdeQXVyNjkxOTM4ODY@._V1_.jpg"));
        Movie movie7 = movieRepo.save(new Movie(null, "Toy Story3", "5+", "Tom Hanks", "Animated Comedy", 103, "https://www.youtube.com/watch?v=JcpWXaA2qeg", "https://m.media-amazon.com/images/M/MV5BMTgxOTY4Mjc0MF5BMl5BanBnXkFtZTcwNTA4MDQyMw@@._V1_.jpg", "https://m.media-amazon.com/images/M/MV5BMTg2NDg5MjY5N15BMl5BanBnXkFtZTcwOTM5MzU1Mw@@._V1_.jpg"));
        Movie movie8 = movieRepo.save(new Movie(null, "Downfall", "R", "Bruno Ganz", "controversial", 156, "https://www.youtube.com/watch?v=htvYfe6wz_8", "https://m.media-amazon.com/images/M/MV5BMTU0NTU5NTAyMl5BMl5BanBnXkFtZTYwNzYwMDg2._V1_.jpg", "https://m.media-amazon.com/images/M/MV5BZjdhZmVkNmYtMzFhYy00NzY0LWE0NjYtNTY3MzFhNzJmOGE3XkEyXkFqcGdeQXVyNzI1NzMxNzM@._V1_.jpg"));
//        Movie movie9 = movieRepo.save(new Movie(null,"Harry Potter and the Philospher's Stone","PG","Daniel Radcliffe","Fantasy",152,"https://www.youtube.com/watch?v=mNgwNXKBEW0","https://www.imdb.com/title/tt0241527/mediaviewer/rm683213568/?ref_=tt_ov_i","https://m.imdb.com/title/tt0241527/mediaviewer/rm3609958656/"));
//        Movie movie10 = movieRepo.save(new Movie(null,"Parasite","R","Kang-ho Song","Comedy Thriller",132,"https://www.youtube.com/watch?v=5xH0HfJHsaY","https://m.imdb.com/title/tt6751668/mediaviewer/rm3194916865/?ref_=ext_shr_lnk","https://www.imdb.com/title/tt6751668/mediaviewer/rm1661231360/"));


        List<CreateScreeningRequest> screenings = new ArrayList<>();
        screenings.add(new CreateScreeningRequest(1, 1, LocalDateTime.now()));
        screenings.add(new CreateScreeningRequest(2, 2, LocalDateTime.now()));
        screenings.add(new CreateScreeningRequest(3, 3, LocalDateTime.now()));
        screenings.add(new CreateScreeningRequest(4, 4, LocalDateTime.now()));
        screenings.add(new CreateScreeningRequest(5, 5, LocalDateTime.now()));
        screenings.add(new CreateScreeningRequest(6, 6, LocalDateTime.now()));
        screenings.add(new CreateScreeningRequest(7, 7, LocalDateTime.now()));
        screenings.add(new CreateScreeningRequest(8, 8, LocalDateTime.now()));

        for (CreateScreeningRequest screening : screenings)
            screeningService.addScreening(screening);

        Role role1 = new Role(Role.Admin);
        Role role2 = new Role(Role.Super_Admin);
        Role role3 = new Role(Role.Client_Admin);
        Role role4 = new Role(Role.User);

//        User user1 = new User("Alex", "password");
//        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
//        user1.addAuthority(role1);
//        user1.addAuthority(role2);
//        user1.addAuthority(role3);
//        user1.addAuthority(role4);
//        userRepo.save(user1);
//
//        User user2 = new User("Peri", "password");
//        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
//        user2.addAuthority(role1);
//        user2.addAuthority(role2);
//        user2.addAuthority(role3);
//        user2.addAuthority(role4);
//        userRepo.save(user2);
//
//        User user3 = new User("JazzyMcJazz", "savemeperi");
//        user3.setPassword(passwordEncoder.encode(user3.getPassword()));
//        user3.addAuthority(role1);
//        user3.addAuthority(role2);
//        user3.addAuthority(role3);
//        user3.addAuthority(role4);
//        userRepo.save(user3);
    }
}
