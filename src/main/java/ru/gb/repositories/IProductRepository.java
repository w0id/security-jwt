package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.gb.data.Product;

public interface IProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
