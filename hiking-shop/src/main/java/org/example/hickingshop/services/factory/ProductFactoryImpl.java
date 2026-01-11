package org.example.hickingshop.services.factory;

import org.example.hickingshop.dto.ProductDto;
import org.example.hickingshop.entities.Jacket;
import org.example.hickingshop.entities.Pants;
import org.example.hickingshop.entities.Product;
import org.example.hickingshop.entities.TShirt;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements IProductFactory{

    @Override
    public Product createProduct(ProductDto productDto) {

        switch (productDto.getType()){
            case TSHIRT -> {
                return createTShirt(productDto);
            }
            case JACKET -> {
                return createJacket(productDto);
            }
            case PANTS -> {
                return createPants(productDto);
            }
        }
        return null;
    }

    private Product createTShirt(ProductDto productDto){
        return TShirt.builder()
                .name(productDto.getName())
                .size(productDto.getSize())
                .quantity(productDto.getQuantity())
                .unitPrice(productDto.getUnitPrice())
                .material(productDto.getMaterial())
                .longSleeves(productDto.getLongSleeves())
                .build();

    }

    private Product createJacket(ProductDto productDto){
        return Jacket.builder()
                .name(productDto.getName())
                .size(productDto.getSize())
                .quantity(productDto.getQuantity())
                .unitPrice(productDto.getUnitPrice())
                .material(productDto.getMaterial())
                .waterproof(productDto.getWaterproof())
                .closureType(productDto.getClosureType())
                .build();
    }

    private Product createPants(ProductDto productDto){
        return Pants.builder()
                .name(productDto.getName())
                .size(productDto.getSize())
                .quantity(productDto.getQuantity())
                .unitPrice(productDto.getUnitPrice())
                .material(productDto.getMaterial())
                .extraPockets(productDto.getExtraPockets())
                .build();
    }
}
