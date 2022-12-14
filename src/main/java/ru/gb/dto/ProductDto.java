package ru.gb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.data.Product;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private Double cost;

    public ProductDto(final String name, final Double cost) {
        this.name = name;
        this.cost = cost;
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.cost = product.getCost();
    }
}