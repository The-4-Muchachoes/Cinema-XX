package com.muchachos.cinemaxx.Security.User.Controller;


import com.muchachos.cinemaxx.Security.Config.JwtTokenUtil;
import com.muchachos.cinemaxx.Security.User.DTO.LoginRequest;
import com.muchachos.cinemaxx.Security.User.Entity.Role;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import com.muchachos.cinemaxx.Security.User.DTO.UserResponse;
import com.muchachos.cinemaxx.Security.User.Service.UserService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Authentication")
@RestController
@RequestMapping(path = "api/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();

    public UserController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid User userRequest) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userRequest.getUsername(), userRequest.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
            System.out.println(user.getAuthorities().toString());

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(user)
                    )
                    .body(modelMapper.map(user, UserResponse.class));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(path = "/signup")
    private ResponseEntity<UserResponse> signup(@RequestBody LoginRequest request) {
        User user = new User(request);
        Role role1 = new Role(Role.User);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.addAuthority(role1);
        return new ResponseEntity<>(
                modelMapper.map(userService.addUser(user), UserResponse.class),
                HttpStatus.CREATED
        );
    }

}
