package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.data.PickUpPoint;

public interface IPickUpPointRepository extends JpaRepository<PickUpPoint, Long> {
}