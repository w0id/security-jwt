package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.core.data.DeliveryType;
import ru.gb.market.core.repositories.IDeliveryTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final IDeliveryTypeRepository deliveryTypeRepository;

    public List<DeliveryType> getTypes() {
        return deliveryTypeRepository.findAll();
    }
}
