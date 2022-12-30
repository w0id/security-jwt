package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.dto.Cart;
import ru.gb.data.Product;
import ru.gb.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void add(Long id) {
        Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Не удается добавить продукт с id: " + id + " в корзину. Продукт не найден"));
        cart.add(product);
    }

    public void delete(Long id) {
        Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Не удается удалить продукт с id: " + id + " из корзины. Продукт не найден"));
        cart.delete(product);
    }

    public void changeQuantity(final Long id, final int quantity) {
        Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Не удается изменить количество. Продукт с id: " + id + " не найден"));
        cart.change(product, quantity);
    }
}
