package org.example.hickingshop.services.iservices;

import jakarta.validation.Valid;
import org.example.hickingshop.dto.OrderDto;
import org.example.hickingshop.dto.OrderProductDto;
import org.example.hickingshop.entities.Order;
import org.example.hickingshop.entities.User;

import java.util.List;

public interface IOrderService {

    Order createOrder(List<OrderProductDto> orderDtos, User customUser);

    Order getOrderDetailById(Long id, Long userId);

    List<Order> getOrdersByUser(Long id);
    Order updateOrder(Order order, List<OrderProductDto> orderDtos);
}
