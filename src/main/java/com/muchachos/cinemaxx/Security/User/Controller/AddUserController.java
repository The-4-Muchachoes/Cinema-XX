package com.muchachos.cinemaxx.Security.User.Controller;

import com.muchachos.cinemaxx.Security.User.Entity.Role;
import com.muchachos.cinemaxx.Security.User.Entity.User;
import com.muchachos.cinemaxx.Security.User.Service.UserService;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class AddUserController {

    private  final UserService userService;
    private  final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/api/new_user")
    private User addUser(@RequestParam String username,@RequestParam String password){
        User user = new User(username, password);
        Role role1 =new Role(Role.User);
        user.setPassword(passwordEncoder.encode(password));
        user.addAuthority(role1);
        return userService.addUser(user);
    }





}
