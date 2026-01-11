package org.example.hickingshop.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.hickingshop.dto.OrderDto;
import org.example.hickingshop.dto.OrderProductDto;
import org.example.hickingshop.dto.UserDto;
import org.example.hickingshop.entities.Product;
import org.example.hickingshop.repository.ProductRepository;
import org.example.hickingshop.services.iservices.IProductService;
import org.example.hickingshop.validators.anotations.MatchesPassword;
import org.example.hickingshop.validators.anotations.ProductOrderQuantity;

import java.util.Optional;

public class ProductOrderValidator implements ConstraintValidator<ProductOrderQuantity, OrderProductDto> {

    private ProductRepository productRepository;

    public ProductOrderValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isValid(OrderProductDto orderDto, ConstraintValidatorContext context) {

        Optional<Product> productOpt = productRepository.findById(orderDto.getProductId());
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (product.getQuantity() < orderDto.getProductQuantity()){
                context.buildConstraintViolationWithTemplate(String.format("The quantity of products %s requested exceeds the quantity in stock.", product.getName())).addConstraintViolation();
                return false;
            }
            return true;
        }
        context.buildConstraintViolationWithTemplate(String.format("Product with id %d not found", orderDto.getProductId())).addConstraintViolation();
        return false;
    }
}
