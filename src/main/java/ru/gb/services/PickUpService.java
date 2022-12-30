package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.data.PickUpPoint;
import ru.gb.repositories.IPickUpPointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PickUpService {

    private final IPickUpPointRepository pickUpPointRepository;

    public List<PickUpPoint> getPickUpPoints() {
        return pickUpPointRepository.findAll();
    }
}
