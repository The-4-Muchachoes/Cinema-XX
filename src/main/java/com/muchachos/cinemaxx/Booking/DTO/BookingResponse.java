package com.muchachos.cinemaxx.Booking.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.muchachos.cinemaxx.Booking.Entity.Booking;
import com.muchachos.cinemaxx.Screening.DTO.ScreeningView;
import com.muchachos.cinemaxx.Seat.DTO.SeatResponse;
import com.muchachos.cinemaxx.Security.User.DTO.UserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {

    private Integer id;
    private List<SeatResponse> seats = new ArrayList<>();
    private ScreeningView screening;
    private Booking.Status status;
    private UserResponse user;
}