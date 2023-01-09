package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.core.data.PickUpPoint;
import ru.gb.market.core.repositories.IPickUpPointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PickUpService {

    private final IPickUpPointRepository pickUpPointRepository;

    public List<PickUpPoint> getPickUpPoints() {
        return pickUpPointRepository.findAll();
    }
}
