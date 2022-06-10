package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    @Query("SELECT C FROM Category C JOIN FETCH C.productList")
    public List<Category> getListCategory();
}
