package com.example.kiemtra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;


    private String image;

    @Nationalized
    private String productName;

    private String slug;

    private String SKU; //ma noi bo san pham giup dinh danh sp

    @Nationalized
    private String describe; //mo ta sp

    @Nationalized
    private String price;

    private int purchases; // luot mua

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "product_discount", joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "discount_id"))
    private Set<Discount> discounts;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Cart> carts;
}
