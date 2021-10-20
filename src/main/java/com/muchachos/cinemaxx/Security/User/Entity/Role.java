package com.muchachos.cinemaxx.Security.User.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

    public static final String User = "USER"; // Movie goer
    public static final String Super_Admin = "SUPER_ADMIN"; // Devs
    public static final String Admin = "ADMIN"; // CinemaXX Main Company
    public static final String Client_Admin = "CLIENT_ADMIN"; //Franchise Cinema

    private String authority;
}
