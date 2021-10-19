package com.muchachos.cinemaxx.Booking.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningDTO;
import com.muchachos.cinemaxx.Seat.DTO.SeatDTO;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewBookingDTO {

    private Integer screeningId;
    private List <Integer> seatIds = new ArrayList<>();
}
