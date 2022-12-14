package ru.gb.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.data.Product;

public class ProductsSpecifications {
    public static Specification<Product> costGreaterThenOrEqualsThen(Double min) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), min);
    }

    public static Specification<Product> costLessThenOrEqualsThen(Double max) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), max);
    }
}
