package ru.gb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.data.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {

    private String username;
    private String password;
    private String email;

    public NewUserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

}
