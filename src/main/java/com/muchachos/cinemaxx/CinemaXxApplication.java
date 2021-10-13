package com.muchachos.cinemaxx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class CinemaXxApplication {

    static Logger logger = LoggerFactory.getLogger(CinemaXxApplication.class);

    public static void main(String[] args) {

        Map<String, String> env = System.getenv();
        env.forEach((k, v) -> System.out.println(k + ":" + v));

        SpringApplication.run(CinemaXxApplication.class, args);
    }

}
