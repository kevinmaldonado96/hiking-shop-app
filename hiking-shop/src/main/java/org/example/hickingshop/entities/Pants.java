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
@DiscriminatorValue("PANTS")
public class Pants extends Product {
    private Boolean extraPockets;

    @Builder
    public Pants(String name, String type, Integer quantity, String size, String material, Double unitPrice, Boolean extraPockets) {
        super(name, type, quantity, size, material, unitPrice);
        this.extraPockets = extraPockets;
    }

    public void updateFromDto(ProductDto productDto) {
        super.updateFromDto(productDto);
        this.extraPockets = productDto.getExtraPockets();
    }
}