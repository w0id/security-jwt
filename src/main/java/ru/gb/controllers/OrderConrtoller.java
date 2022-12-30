package ru.gb.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.DeliveryType;
import ru.gb.data.PickUpPoint;
import ru.gb.dto.CustomerDto;
import ru.gb.services.OrderService;
import ru.gb.wrappers.OrderWrapper;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@EnableGlobalAuthentication
@AllArgsConstructor
public class OrderConrtoller {

    private final OrderService orderService;

    @GetMapping
    public CustomerDto getCustomer(Principal principal) {
        return new CustomerDto(orderService.getCustomer(principal));
    }

    @GetMapping("/delivery_types")
    public List<DeliveryType> getTypes() {
        return orderService.getTypes();
    }

    @GetMapping("/pickup_points")
    public List<PickUpPoint> getPickUpPoints() {
        return orderService.getPickUpPoints();
    }

    @PostMapping
    public void createOrder(@RequestBody OrderWrapper orderWrapper) {
        orderService.createOrder(orderWrapper);
    }
}
