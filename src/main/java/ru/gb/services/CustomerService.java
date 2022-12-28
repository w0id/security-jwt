package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.data.Customer;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserService userService;
    public Customer getCustomerDetails(final Long id) {
        return userService.getCustomer(id);
    }
}
