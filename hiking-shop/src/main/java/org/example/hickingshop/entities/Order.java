package org.example.hickingshop.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "status")
    private String status;

    // Relación con products (muchos a muchos)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Order(String orderNumber, Double totalAmount, Integer totalQuantity, User user, String status) {
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
        this.user = user;
        this.status = status;
        this.orderProducts = new ArrayList<>();
    }

    public void clearOderProducts() {
        orderProducts.clear();
    }

    public void addAllOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts.addAll(orderProducts);
    }
}
