package ru.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.User;
import ru.gb.dto.NewUserDto;
import ru.gb.services.RegisterService;

@RestController
@RequestMapping("/register")
@EnableGlobalAuthentication
@AllArgsConstructor
public class RegisterController {
    private RegisterService registerService;

    @PostMapping
    public NewUserDto registerUser(@RequestBody NewUserDto user) {
        registerService.save(new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getEmail()));
        return user;
    }
}