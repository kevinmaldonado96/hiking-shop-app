package org.example.hickingshop.services.factory;

import org.example.hickingshop.dto.ProductDto;
import org.example.hickingshop.entities.Product;

public interface IProductFactory {

    Product createProduct(ProductDto productDto);

}
