package org.example.hickingshop.facade;

import org.example.hickingshop.configuration.custom.CustomUser;
import org.example.hickingshop.dto.OrderDto;
import org.example.hickingshop.dto.OrderProductDto;
import org.example.hickingshop.dto.ProductActionDto;
import org.example.hickingshop.dto.ProductDto;
import org.example.hickingshop.dto.enums.ProductQuantityActions;
import org.example.hickingshop.entities.Order;
import org.example.hickingshop.entities.OrderProduct;
import org.example.hickingshop.entities.Product;
import org.example.hickingshop.entities.User;
import org.example.hickingshop.services.OrderService;
import org.example.hickingshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OrderProductFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    public Order createOrder(List<OrderProductDto> orderDtos, User user) {

        Order order = orderService.createOrder(orderDtos, user);
        orderDtos.forEach(orderDto -> productService.updateProductQuantity(orderDto.getProductQuantity(), ProductQuantityActions.REMOVE, orderDto.getProductId()));
        return order;
    }

    public Order updateOrder(List<OrderProductDto> orderProductDtos, Long orderId, Long userId) {

        Order order = orderService.getOrderDetailById(orderId, userId);

        Map<Long, OrderProductDto> mapOrderProductDtos = orderProductDtos.stream()
                .collect(Collectors.toMap(OrderProductDto::getProductId, dto -> dto));

        List<ProductActionDto> productActions = obtainActionProducts(mapOrderProductDtos, order.getOrderProducts());

        Order orderUpdated = orderService.updateOrder(order, orderProductDtos);

        productActions.forEach((dto) -> productService.updateProductQuantity(dto.getOrderProductDto().getProductQuantity(), dto.getAction(), dto.getOrderProductDto().getProductId()));

        return orderUpdated;
    }

    private List<ProductActionDto> obtainActionProducts(Map<Long, OrderProductDto> mapOrderProductDtos, List<OrderProduct> orderProducts) {

        List<ProductActionDto> productActionDtos = new ArrayList<>();

        for(OrderProduct orderProduct : orderProducts) {
            if(!mapOrderProductDtos.containsKey(orderProduct.getProduct().getId())) {
                productActionDtos.add(new ProductActionDto(ProductQuantityActions.ADD, new OrderProductDto(orderProduct.getProductQuantity(), orderProduct.getProduct().getId())));
            }else{
                Integer oldQuantity = orderProduct.getProductQuantity();
                Integer newQuantity = mapOrderProductDtos.get(orderProduct.getProduct().getId()).getProductQuantity();

                if (newQuantity > oldQuantity) {
                    productActionDtos.add(new ProductActionDto(ProductQuantityActions.REMOVE, mapOrderProductDtos.get(orderProduct.getProduct().getId())));
                }else {
                    productActionDtos.add(new ProductActionDto(ProductQuantityActions.ADD, mapOrderProductDtos.get(orderProduct.getProduct().getId())));
                }
            }
        }
        return productActionDtos;
    }
}
