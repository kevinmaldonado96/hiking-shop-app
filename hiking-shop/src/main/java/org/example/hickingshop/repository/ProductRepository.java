package org.example.hickingshop.repository;

import org.example.hickingshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("Select p.unitPrice from Product p where p.id = :id")
    Integer findPriceByProductId(Long id);

}
