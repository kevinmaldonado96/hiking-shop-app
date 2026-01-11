package org.example.hickingshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hickingshop.dto.enums.ProductQuantityActions;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductActionDto {

   private ProductQuantityActions action;
   private OrderProductDto orderProductDto;


}
