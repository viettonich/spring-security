package com.example.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.app.dto.ProductDto;
import com.example.app.repository.ProductRepositoryCustom;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductDto> getProductListByCategoryId(Long categoryId) {
        return entityManager.createQuery(
                "SELECT new com.example.app.dto.ProductDto(p.name, c.name) FROM Product p INNER JOIN Category c ON p.category.id = c.id WHERE p.category.id = :categoryId",
                ProductDto.class).setParameter("categoryId", categoryId).getResultList();
    }

}
