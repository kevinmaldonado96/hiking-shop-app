package org.example.hickingshop.services;

import org.example.hickingshop.dto.ProductDto;
import org.example.hickingshop.dto.enums.ProductQuantityActions;
import org.example.hickingshop.entities.Product;
import org.example.hickingshop.exception.exceptions.NotFoundDataException;
import org.example.hickingshop.repository.ProductRepository;
import org.example.hickingshop.services.factory.IProductFactory;
import org.example.hickingshop.services.iservices.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductFactory productFactory;

    public Product createProduct(ProductDto productDto){
        Product productToSave = productFactory.createProduct(productDto);
        return productRepository.save(productToSave);
    }

    public Product updateProduct(ProductDto productDto, Long id){
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Producto no encontrado"));

        productToUpdate.updateFromDto(productDto);

        return productRepository.save(productToUpdate);
    }

    @Override
    public Product updateProductQuantity(Integer quantity, ProductQuantityActions action,  Long id){
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Producto no encontrado"));
        if (action.equals(ProductQuantityActions.ADD)){
            productToUpdate.setQuantity(productToUpdate.getQuantity() + quantity);
        } else{
            productToUpdate.setQuantity(productToUpdate.getQuantity() - quantity);
        }

        return productRepository.save(productToUpdate);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundDataException("Producto no encontrado"));
    }


}
