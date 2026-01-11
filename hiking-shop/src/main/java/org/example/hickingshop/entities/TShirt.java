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
@DiscriminatorValue("TSHIRT")
public class TShirt extends Product {
    private Boolean longSleeves;

    @Builder
    public TShirt(String name, String size, String type, Integer quantity, String material,
                  Double unitPrice, Boolean longSleeves) {
        super(name, type, quantity, size, material, unitPrice);
        this.longSleeves = longSleeves;
    }

    public void updateFromDto(ProductDto dto) {
        super.updateFromDto(dto);
        this.longSleeves = dto.getLongSleeves();
    }
}