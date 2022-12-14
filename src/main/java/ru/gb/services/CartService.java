package ru.gb.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.data.Cart;
import ru.gb.data.Product;
import ru.gb.repositories.IProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private IProductRepository productRepository;

    private Cart cart;


    public List<Product> getCartItems() {
        return cart.getCartItems();
    }

    public List<Product> addToCart(Product product) {
        return cart.addProduct(product);
    }

    public List<Product> deleteFromCart(Long id, String name, Double cost) {
        Product product = new Product(id, name, cost);
        return cart.delProduct(product);
    }
}
