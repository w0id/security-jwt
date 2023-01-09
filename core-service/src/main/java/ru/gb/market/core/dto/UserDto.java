package ru.gb.market.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.core.data.Customer;
import ru.gb.market.core.data.Role;
import ru.gb.market.core.data.User;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private Customer customer;
    private Collection<Role> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles();
        this.customer = user.getCustomer();
    }

}
