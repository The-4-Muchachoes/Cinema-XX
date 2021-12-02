package com.muchachos.cinemaxx.Security.User.Service;

import com.muchachos.cinemaxx.Security.Config.JwtTokenUtil;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import com.muchachos.cinemaxx.Security.User.Repo.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtTokenUtil jwtTokenUtil;

    public UserServiceImpl(UserRepo userRepo, JwtTokenUtil jwtTokenUtil) {
        this.userRepo = userRepo;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getAuthenticatedUser(String token) {
        String bearer = "Bearer ";
        if (token.startsWith(bearer))
            token = token.replace(bearer, "");

        String username = jwtTokenUtil.getUsername(token);
        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }
}
