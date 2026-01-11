package org.example.hickingshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hickingshop.validators.anotations.ProductOrderQuantity;

@Data
@NoArgsConstructor
@ProductOrderQuantity
public class OrderProductDto {

    @NotNull
    private Integer productQuantity;

    @NotNull
    private Long productId;

    public OrderProductDto(Integer productQuantity, Long productId) {
        this.productQuantity = productQuantity;
        this.productId = productId;
    }
}
