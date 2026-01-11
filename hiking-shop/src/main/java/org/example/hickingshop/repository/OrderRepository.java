package org.example.hickingshop.repository;

import org.example.hickingshop.entities.Order;
import org.example.hickingshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndUserId(Long id, Long userId);

    List<Order> findAllByUserId(Long userId);

    List<Order> getOrderById(Long id);

    Long user(User user);
}
