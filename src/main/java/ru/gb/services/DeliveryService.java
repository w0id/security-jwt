package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.data.DeliveryType;
import ru.gb.repositories.IDeliveryTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final IDeliveryTypeRepository deliveryTypeRepository;

    public List<DeliveryType> getTypes() {
        return deliveryTypeRepository.findAll();
    }
}
