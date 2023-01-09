package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.data.DeliveryType;

public interface IDeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
}