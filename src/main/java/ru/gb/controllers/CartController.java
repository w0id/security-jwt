package ru.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.data.Product;
import ru.gb.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart_items")
@AllArgsConstructor
public class CartController {
    private CartService cartService;

    @GetMapping
    public List<Product> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping
    public List<Product> addToCart(@RequestBody Product product) {
        return cartService.addToCart(product);
    }

    @DeleteMapping
    public List<Product> deleteFromCart(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam Double cost
    ) {
        return cartService.deleteFromCart(id, name, cost);
    }
}