package ru.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.dto.CustomerDto;
import ru.gb.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@EnableGlobalAuthentication
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDto getCustomerDetails(@PathVariable Long id) {
        return new CustomerDto(customerService.getCustomerDetails(id));
    }

}
