package com.example.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.example.app.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "find_product_category_dto",
        query = "SELECT p.name as productName, c.name as categoryName FROM product p INNER JOIN category c ON p.category_id = c.id",
        resultSetMapping = "product_category_dto"
    )
    @SqlResultSetMapping(
        name = "product_category_dto",
        classes = @ConstructorResult(
            targetClass = ProductDto.class,
            columns = {
                @ColumnResult(name = "productName", type = String.class),
                @ColumnResult(name = "categoryName", type = String.class),
            }
        )
    )
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
