package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.data.DeliveryType;

public interface IDeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
}