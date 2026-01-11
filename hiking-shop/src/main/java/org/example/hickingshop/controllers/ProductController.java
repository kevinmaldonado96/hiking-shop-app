package org.example.hickingshop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.hickingshop.controllers.utils.ControllerUtils;
import org.example.hickingshop.dto.ProductDto;
import org.example.hickingshop.entities.Product;
import org.example.hickingshop.services.ProductService;
import org.example.hickingshop.services.iservices.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ControllerUtils controllerUtils;

    @Autowired
    private IProductService productService;

    @Operation(
            summary = "Crear un nuevo producto",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(controllerUtils.getErrors(bindingResult));
        }
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @Operation(
            summary = "Actualizar un producto",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(controllerUtils.getErrors(bindingResult));
        }
        return ResponseEntity.ok(productService.updateProduct(productDto, id));
    }

    @Operation(
            summary = "Obtener todos los productos",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(
            summary = "Obtener producto por id",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }



}
