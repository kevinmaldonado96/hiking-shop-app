package org.example.hickingshop.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hickingshop.dto.ProductDto;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("JACKET")
public class Jacket extends Product {

    private Boolean waterproof;
    private String closureType;

    @Builder
    public Jacket(String name, String type, Integer quantity, String size, String material, Double unitPrice, Boolean waterproof, String closureType) {
        super(name, type, quantity, size, material, unitPrice);
        this.waterproof = waterproof;
        this.closureType = closureType;
    }

    public void updateFromDto(ProductDto productDto) {
        super.updateFromDto(productDto);
        this.waterproof = productDto.getWaterproof();
        this.closureType = productDto.getClosureType();
    }
}