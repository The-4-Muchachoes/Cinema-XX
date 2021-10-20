package com.muchachos.cinemaxx.testUtils;

import com.muchachos.cinemaxx.Cinema.Entity.Cinema;
import com.muchachos.cinemaxx.Cinema.Repo.CinemaRepo;
import com.muchachos.cinemaxx.Movie.Entity.Movie;
import com.muchachos.cinemaxx.Movie.Repo.MovieRepo;
import com.muchachos.cinemaxx.Screening.DTO.CreateScreeningRequest;
import com.muchachos.cinemaxx.Screening.Entity.Screening;
import com.muchachos.cinemaxx.Screening.Service.ScreeningService;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import com.muchachos.cinemaxx.Theater.Repo.TheaterRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataMaker {

    public static List<Integer> makeMovies(MovieRepo movieRepo) {
        movieRepo.deleteAll();
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

//            screeningService.addScreening(screening1);
//            screeningService.addScreening(screening2);
        }

        return ids;
    }

    public static void makeTestData(CinemaRepo cinemaRepo, TheaterRepo theaterRepo,
                               MovieRepo movieRepo, ScreeningService screeningService) {

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

        Movie movie1 = movieRepo.save(new Movie(null, "Ironman", "12+", "Brand Pit", "Very good", 95, "https://youtu.be/8ugaeA-nMTc", "https://www.imdb.com/title/tt0371746/mediaviewer/rm1544850432/?ref_=tt_ov_i", "https://www.imdb.com/title/tt0371746/mediaviewer/rm320113664/?ref_=ext_shr_lnk"));
        Movie movie2 = movieRepo.save(new Movie(null, "Spiderman", "12+", "Cameron Diaz", "Amazing", 98, "https://www.youtube.com/watch?v=TYMMOjBUPMM", "https://m.imdb.com/title/tt0145487/mediaviewer/rm3632146944/?fbclid=IwAR1R_iqLbOlVBVZ0IosrxRXQ89blCJKmarwSInP7MZEzCWS-B6JXoM73rGc", "https://m.imdb.com/title/tt0145487/mediaviewer/rm1262040064/"));
        Movie movie3 = movieRepo.save(new Movie(null, "The Live of Others", "R", "Ulrich Muhe", "Thriller", 137, "https://www.youtube.com/watch?v=n3_iLOp6IhM", "https://m.imdb.com/title/tt0405094/mediaviewer/rm673410048/?ref_=ext_shr_lnk", "https://m.imdb.com/title/tt0405094/mediaviewer/rm1748998144/"));
        Movie movie4 = movieRepo.save(new Movie(null, "Inception", "PG-13", "Leonardo DiCaprio", "Thriller", 148, "https://www.youtube.com/watch?v=YoHD9XEInc0", "https://m.imdb.com/title/tt1375666/mediaviewer/rm3426651392/?ref_=ext_shr_lnk", "https://m.imdb.com/title/tt1375666/mediaviewer/rm3574566144/?fbclid=IwAR3iw6ZA9ZbTB63bEiqe6K5hNPr_CbmUUBHFfqg2yklLrdcjkLUau4SPqSo"));
        Movie movie5 = movieRepo.save(new Movie(null, "Queen of Katwe", "PG", "Madina Nalwanga", "Inspiring", 124, "https://www.youtube.com/watch?v=z4l3-_yub5A", "https://m.imdb.com/title/tt4341582/mediaviewer/rm1254426880/?ref_=ext_shr_lnk", "https://m.imdb.com/title/tt4341582/mediaviewer/rm3887608320/"));
        Movie movie6 = movieRepo.save(new Movie(null, "A Separation", "PG-13", "Payman Maadi", "Genre Drama", 122, "https://www.youtube.com/watch?v=58Onuy5USTc", "https://m.imdb.com/title/tt1832382/mediaviewer/rm1048720128/?ref_=ext_shr_lnk", "https://www.imdb.com/title/tt1832382/mediaviewer/rm936849152/"));
        Movie movie7 = movieRepo.save(new Movie(null, "Toy Story3", "5+", "Tom Hanks", "Animated Comedy", 103, "https://www.youtube.com/watch?v=JcpWXaA2qeg", "https://www.imdb.com/title/tt0435761/mediaviewer/rm3038678784/?ref_=tt_ov_i", "https://www.imdb.com/title/tt0435761/mediaviewer/rm336232960/"));
        Movie movie8 = movieRepo.save(new Movie(null, "Downfall", "R", "Bruno Ganz", "controversial", 156, "https://www.youtube.com/watch?v=htvYfe6wz_8", "https://m.imdb.com/title/tt0363163/mediaviewer/rm2017302528/?ref_=ext_shr_lnk", "https://www.imdb.com/title/tt0363163/mediaviewer/rm706325505/"));
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
    }
}
