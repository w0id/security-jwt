package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.core.data.*;
import ru.gb.market.core.exceptions.UnauthorizedUserException;
import ru.gb.market.core.repositories.IOrderRepository;
import ru.gb.market.core.wrappers.OrderWrapper;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final DeliveryService deliveryService;
    private final PickUpService pickUpService;
    private final ProductService productService;
    private final IOrderRepository orderRepository;

    public Customer getCustomer(Principal principal) {
        try {
            return userService.findByUsername(principal.getName()).get().getCustomer();
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

    @Transactional
    public void createOrder(final OrderWrapper orderWrapper) {
        Collection<Customer> customers = new ArrayList<>();
        customers.add(orderWrapper.getCustomer());
        Collection<DeliveryType> deliveryTypes = new ArrayList<>();
        deliveryTypes.add(orderWrapper.getDeliveryType());
        Order order = new Order();
        if (null != orderWrapper.getPickUpPoint().getId()) {
            Collection<PickUpPoint> pickUpPoints = new ArrayList<>();
            pickUpPoints.add(orderWrapper.getPickUpPoint());
            order.setPickUpPoint(orderWrapper.getPickUpPoint());
        }
        order.setCustomer(orderWrapper.getCustomer());
        order.setDeliveryType(orderWrapper.getDeliveryType());
        order.setOrderItems(orderWrapper.getCart().getItems().stream().map(
                        cartItem -> {
                            return new OrderItem(
                                    productService.getProduct(cartItem.getId()).get(),
                                    order,
                                    cartItem.getQuantity()
                            );
                        }
                ).collect(Collectors.toList())
        );
        orderRepository.save(order);
    }
}
