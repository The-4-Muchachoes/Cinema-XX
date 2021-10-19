package com.muchachos.cinemaxx.Booking.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingScreeningDTO {

    String movie;
    String theater;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    LocalDateTime startTime;


}
