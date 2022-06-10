package com.example.app.repository;

import java.util.List;

import com.example.app.dto.ProductDto;

public interface ProductRepositoryCustom {
    
    public List<ProductDto> getProductListByCategoryId(Long categoryId);
}
