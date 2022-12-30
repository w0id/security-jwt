package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.data.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}