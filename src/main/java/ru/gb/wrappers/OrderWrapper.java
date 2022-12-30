package ru.gb.wrappers;

import lombok.Data;
import ru.gb.data.Customer;
import ru.gb.data.DeliveryType;
import ru.gb.data.PickUpPoint;
import ru.gb.dto.Cart;

@Data
public class OrderWrapper {

    private Customer customer;
    private Cart cart;
    private DeliveryType deliveryType;
    private PickUpPoint pickUpPoint;

}
