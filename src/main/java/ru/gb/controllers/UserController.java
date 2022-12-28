package ru.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.User;
import ru.gb.dto.UserDto;
import ru.gb.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
@EnableGlobalAuthentication
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return new UserDto(userService.getUser(id));
    }

    @GetMapping
    public Page<UserDto> getUser(
            @RequestParam(value = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }
        return userService.getUserFilter(page).map(
                UserDto::new
        );
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.save(new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getCustomer(), user.getRoles()));
    }

    @PutMapping
    public void editUser(@RequestBody User user) {
        userService.editUser(new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getCustomer(), user.getRoles()));
    }
}