package org.example.hickingshop.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hickingshop.dto.ProductDto;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "products")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(insertable = false, updatable = false)
    private String type;

    private String size;

    private Integer quantity;
    private LocalDateTime createDate;
    private String material;
    private Double unitPrice;


    public Product(String name, String type, Integer quantity, String size, String material, Double unitPrice) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.size = size;
        this.material = material;
        this.unitPrice = unitPrice;
    }

    public void updateFromDto(ProductDto dto) {
        this.name = dto.getName();
        this.type = dto.getType().toString();
        this.size = dto.getSize();
        this.quantity = dto.getQuantity();
        this.material = dto.getMaterial();
        this.unitPrice = dto.getUnitPrice();
    }
}
