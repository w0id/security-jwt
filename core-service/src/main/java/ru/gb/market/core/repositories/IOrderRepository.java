package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.data.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}