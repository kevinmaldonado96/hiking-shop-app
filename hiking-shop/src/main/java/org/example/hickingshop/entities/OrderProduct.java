package org.example.hickingshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders_products")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Builder
    public OrderProduct(Product product, Order order, Integer productQuantity) {
        this.product = product;
        this.order = order;
        this.productQuantity = productQuantity;
    }
}

