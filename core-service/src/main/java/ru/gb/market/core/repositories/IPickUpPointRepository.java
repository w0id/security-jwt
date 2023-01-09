package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.core.data.PickUpPoint;

public interface IPickUpPointRepository extends JpaRepository<PickUpPoint, Long> {
}