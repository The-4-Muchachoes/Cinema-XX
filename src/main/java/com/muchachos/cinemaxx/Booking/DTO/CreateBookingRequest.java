package com.muchachos.cinemaxx.Booking.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingRequest {

    private Integer screeningId;
    private List <Integer> seatIds = new ArrayList<>();
}
