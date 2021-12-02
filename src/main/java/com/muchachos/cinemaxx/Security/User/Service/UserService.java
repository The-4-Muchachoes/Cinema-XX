package com.muchachos.cinemaxx.Security.User.Service;

import com.muchachos.cinemaxx.Security.User.Entity.User;

public interface UserService {
    User addUser(User user);
    User getAuthenticatedUser(String jwtToken);
}
