package com.example.kiemtra.repostitory;

import com.example.kiemtra.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.slug like %?1%")
    List<Product> findBySlugContaining(String string);

    List<Product> findByPurchasesGreaterThanEqual(Integer number);

    @Query("select p from Product p where p.SKU = ?1")
    Product findBySKU(String SKU);
}
