package com.muchachos.cinemaxx.Security.User.DTO;


import lombok.Data;
@Data
public class LoginResponse {

    private String id;
    private String username;
    private String accessToken;
}