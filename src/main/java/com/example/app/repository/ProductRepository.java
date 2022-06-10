package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.dto.ProductDto;
import com.example.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    
    //@Query("SELECT new com.example.app.dto.ProductDto(p.name, c.name) FROM Product p INNER JOIN Category c ON p.category.id = c.id")
    @Query(name = "find_product_category_dto", nativeQuery = true)
    public List<ProductDto> getListProduct();

}
