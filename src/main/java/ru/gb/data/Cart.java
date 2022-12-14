package ru.gb.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private final List<Product> cart = new ArrayList<>();

    public List<Product> addProduct(Product product) {
        this.cart.add(product);
        return this.cart;
    }

    public List<Product> delProduct(Product product) {
        this.cart.remove(product);
        return this.cart;
    }

    public List<Product> getCartItems() {
        return this.cart;
    }
}
