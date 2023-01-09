package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.data.OrderDetail;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}