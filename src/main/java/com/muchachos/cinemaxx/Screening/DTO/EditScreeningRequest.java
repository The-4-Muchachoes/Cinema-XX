package com.muchachos.cinemaxx.Screening.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditScreeningRequest {

    int id;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    @NonNull LocalDateTime startTime;
}
