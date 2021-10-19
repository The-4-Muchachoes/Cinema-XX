package com.muchachos.cinemaxx.Seat.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.muchachos.cinemaxx.Seat.Entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditSeatsRequest {

    List<Integer> seatIds;
    Seat.Status status;
}
