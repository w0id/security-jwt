package ru.gb.market.core.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.core.dto.Cart;
import ru.gb.market.core.services.CartService;


@RestController
@RequestMapping("/api/v1/cart_items")
@EnableGlobalAuthentication
@AllArgsConstructor
@CrossOrigin("*")
public class CartController {
    private CartService cartService;

    @GetMapping
    public Cart getCartItems() {
        return cartService.getCurrentCart();
    }

    @PostMapping
    public void addToCart(@RequestParam Long id) {
        cartService.add(id);
    }

    @PutMapping
    public void changeQuantity(@RequestParam Long id,
                               @RequestParam int quantity) {
        cartService.changeQuantity(id, quantity);
    }

    @DeleteMapping
    public void deleteFromCart(@RequestParam Long id) {
        cartService.delete(id);
    }
}