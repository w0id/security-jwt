package ru.gb.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.data.*;
import ru.gb.exceptions.UnauthorizedUserException;
import ru.gb.repositories.IOrderDetailRepository;
import ru.gb.wrappers.OrderWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final JwtService jwtService;
    private final UserService userService;
    private final DeliveryService deliveryService;
    private final PickUpService pickUpService;
    private final ProductService productService;
    private final IOrderDetailRepository orderDetailRepository;

    public Customer getCustomer(String authorization) {
        try {
            String bearerTokenValue = authorization.substring(7);
            String username = jwtService.getUsername(bearerTokenValue);
            return userService.getCustomer(userService.findByUsername(username).get().getId());
        } catch (UnauthorizedUserException e) {
            throw new UnauthorizedUserException("Пользователь не авторизован");
        }
    }

    public List<DeliveryType> getTypes() {
        return deliveryService.getTypes();
    }

    public List<PickUpPoint> getPickUpPoints() {
        return pickUpService.getPickUpPoints();
    }

    public void storeOrder(final OrderWrapper orderWrapper) {
        Collection<OrderItem> orderItems = new ArrayList<>();
        orderWrapper.getCart().getItems().stream().forEach(i -> {
            orderItems.add(new OrderItem(productService.getProduct(i.getId()).get(),i.getQuantity()));
        });
        Collection<Customer> customers = new ArrayList<>();
        customers.add(orderWrapper.getCustomer());
        Collection<DeliveryType> deliveryTypes = new ArrayList<>();
        deliveryTypes.add(orderWrapper.getDeliveryType());
        OrderDetail orderDetail = new OrderDetail();
        if (null != orderWrapper.getPickUpPoint().getId()) {
            Collection<PickUpPoint> pickUpPoints = new ArrayList<>();
            pickUpPoints.add(orderWrapper.getPickUpPoint());
            orderDetail = new OrderDetail(orderWrapper.getCustomer(), orderWrapper.getDeliveryType(), orderWrapper.getPickUpPoint(), orderItems);
        } else {
            orderDetail = new OrderDetail(orderWrapper.getCustomer(), orderWrapper.getDeliveryType(), orderItems);
        }
        orderDetailRepository.save(orderDetail);
    }
}
