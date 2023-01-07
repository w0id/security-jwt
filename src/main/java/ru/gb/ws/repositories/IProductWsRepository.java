package ru.gb.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.gb.data.Product;

import java.util.Optional;

public interface IProductWsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
