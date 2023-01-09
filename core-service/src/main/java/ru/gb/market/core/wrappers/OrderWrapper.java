package ru.gb.market.core.wrappers;

import lombok.Data;
import ru.gb.market.core.data.Customer;
import ru.gb.market.core.data.DeliveryType;
import ru.gb.market.core.data.PickUpPoint;
import ru.gb.market.core.dto.Cart;

@Data
public class OrderWrapper {

    private Customer customer;
    private Cart cart;
    private DeliveryType deliveryType;
    private PickUpPoint pickUpPoint;

}
