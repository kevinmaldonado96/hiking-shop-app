package org.example.hickingshop.services;

import org.example.hickingshop.dto.OrderDto;
import org.example.hickingshop.dto.OrderProductDto;
import org.example.hickingshop.dto.enums.OrderStatus;
import org.example.hickingshop.entities.Order;
import org.example.hickingshop.entities.OrderProduct;
import org.example.hickingshop.entities.Product;
import org.example.hickingshop.entities.User;
import org.example.hickingshop.exception.exceptions.NotFoundDataException;
import org.example.hickingshop.repository.OrderRepository;
import org.example.hickingshop.repository.ProductRepository;
import org.example.hickingshop.repository.UserRepository;
import org.example.hickingshop.services.iservices.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private Order order;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(List<OrderProductDto> orderDtos, User customUser){

        Double totalAmount = orderDtos.stream().mapToDouble(orderDto -> (productRepository.findPriceByProductId(orderDto.getProductId()) * orderDto.getProductQuantity() )).sum();
        Integer totalQuantity = orderDtos.stream().mapToInt(OrderProductDto::getProductQuantity).sum();

        User user = userRepository.getReferenceById(customUser.getId());

        String orderNumber = UUID.randomUUID().toString();

        order = Order.builder()
                .orderNumber(orderNumber)
                .totalAmount(totalAmount)
                .totalQuantity(totalQuantity)
                .user(user)
                .status(OrderStatus.CREATED.name())
                .build();

        List<OrderProduct> orderProducts = orderDtos.stream().map(this::mapOrderDtoToOrder).collect(Collectors.toList());
        order.setOrderProducts(orderProducts);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderDetailById(Long id, Long userId) {
        return orderRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundDataException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public Order updateOrder(Order order, List<OrderProductDto> orderDtos) {

        this.order = order;

        Double totalAmount = orderDtos.stream().mapToDouble(orderDto -> (productRepository.findPriceByProductId(orderDto.getProductId()) * orderDto.getProductQuantity() )).sum();
        Integer totalQuantity = orderDtos.stream().mapToInt(OrderProductDto::getProductQuantity).sum();

        List<OrderProduct> orderProducts = orderDtos.stream().map(this::mapOrderDtoToOrder).collect(Collectors.toList());

        order.clearOderProducts();
        order.addAllOrderProducts(orderProducts);
        order.setTotalAmount(totalAmount);
        order.setTotalQuantity(totalQuantity);

        return orderRepository.save(order);
    }


    private OrderProduct mapOrderDtoToOrder(OrderProductDto orderDto){

        Product product = productRepository.getReferenceById(orderDto.getProductId());

        return OrderProduct
                .builder()
                .order(order)
                .product(product)
                .productQuantity(orderDto.getProductQuantity())
                .build();

    }

}
