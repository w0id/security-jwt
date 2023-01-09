package ru.gb.market.core.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.core.data.Product;
import ru.gb.market.core.dto.Cart;
import ru.gb.market.core.exceptions.ResourceNotFoundException;

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
