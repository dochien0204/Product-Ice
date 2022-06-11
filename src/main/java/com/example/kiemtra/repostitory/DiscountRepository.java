package com.example.kiemtra.repostitory;

import com.example.kiemtra.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findByDiscountName(String discountName);
}
