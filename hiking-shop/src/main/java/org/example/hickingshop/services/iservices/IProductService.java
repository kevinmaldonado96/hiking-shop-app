package org.example.hickingshop.services.iservices;

import org.example.hickingshop.dto.ProductDto;
import org.example.hickingshop.dto.enums.ProductQuantityActions;
import org.example.hickingshop.entities.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(ProductDto productDto);
    Product updateProduct(ProductDto productDto, Long id);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProductQuantity(Integer quantity, ProductQuantityActions action, Long id);

}
