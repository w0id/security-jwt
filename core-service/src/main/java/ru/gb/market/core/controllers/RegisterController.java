package ru.gb.market.core.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.core.data.User;
import ru.gb.market.core.dto.NewUserDto;
import ru.gb.market.core.services.RegisterService;

@RestController
@RequestMapping("/register")
@EnableGlobalAuthentication
@AllArgsConstructor
@CrossOrigin("*")
public class RegisterController {
    private RegisterService registerService;

    @PostMapping
    public NewUserDto registerUser(@RequestBody NewUserDto user) {
        registerService.save(new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getCustomer()));
        return user;
    }
}