package org.example.hickingshop.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hickingshop.dto.enums.ProductType;
import org.example.hickingshop.validators.anotations.MatchesPassword;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private ProductType type;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double unitPrice;

    //tshirt
    private String size;
    private String material;
    private Boolean longSleeves;

    //pants
    private String pantsType;
    private Boolean extraPockets;

    //jacket
    private Boolean waterproof;
    private String closureType;

    public ProductDto(Integer quantity, Long id) {
        this.quantity = quantity;
        this.id = id;
    }
}
