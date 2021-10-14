package com.muchachos.cinemaxx.Screening.DTO;

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
public class ScreeningDTO {

    Integer id;
    Integer movieId;
    Integer theaterId;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    LocalDateTime startTime;
}
