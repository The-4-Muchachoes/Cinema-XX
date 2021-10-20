package com.muchachos.cinemaxx.Seat.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatResponse {

    private Integer id;
    private Integer row;
    private Integer seatNo;
    private Seat.Status status;

}
