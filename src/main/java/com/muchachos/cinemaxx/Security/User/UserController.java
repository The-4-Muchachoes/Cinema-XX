package com.muchachos.cinemaxx.Security.User;


import com.muchachos.cinemaxx.Security.JwtTokenUtil;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Authentication")
@RestController
@RequestMapping(path = "api/public")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    ModelMapper modelMapper = new ModelMapper();


    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid User userRequest) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userRequest.getUsername(), userRequest.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(user)
                    )
                    .body(modelMapper.map(user,UserDTO.class));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
