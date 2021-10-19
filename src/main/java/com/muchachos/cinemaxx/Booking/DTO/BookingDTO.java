package com.muchachos.cinemaxx.Booking.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.muchachos.cinemaxx.Seat.DTO.SeatDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Integer id;
    private List<SeatDTO> seats = new ArrayList<>();
    private BookingScreeningDTO screening;
}
