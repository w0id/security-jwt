package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.data.OrderDetail;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}