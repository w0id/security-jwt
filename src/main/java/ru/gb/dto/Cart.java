package ru.gb.dto;

import lombok.Data;
import ru.gb.data.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: Добавить поля создания/изменения во все таблицы
@Data
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalCost;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
        items.stream().filter(i -> i.getId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(i -> {
                    items.set(items.indexOf(i),new CartItem(product.getId(), product.getName(), i.getQuantity()+1, product.getCost(), product.getCost().multiply(BigDecimal.valueOf(i.getQuantity() + 1))));
                }, () -> {
                    items.add(new CartItem(product.getId(), product.getName(), 1, product.getCost(), product.getCost()));
                });
        recalculate();
    }

    public void delete(Product product) {
        items.removeIf(cartItem -> product.getId().equals(cartItem.getId()));
        recalculate();
    }

    private void recalculate() {
        totalCost = new BigDecimal(0);
        items.forEach(cartItem -> totalCost = totalCost.add(cartItem.getCost()));
        BigDecimal i = totalCost;
    }

    public void change(final Product product, final int quantity) {
        items.stream().filter(i -> i.getId().equals(product.getId()))
                .findFirst()
                .ifPresent(i -> {
                    if ((i.getQuantity() + quantity)==0) {
                        items.remove(items.indexOf(i));
                    } else {
                        items.set(items.indexOf(i),new CartItem(product.getId(), product.getName(), i.getQuantity()+quantity, product.getCost(), product.getCost().multiply(BigDecimal.valueOf(i.getQuantity() + quantity))));
                    }
                });
        recalculate();
    }
}
