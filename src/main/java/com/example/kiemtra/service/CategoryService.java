package com.example.kiemtra.service;

import com.example.kiemtra.dto.CategoryDTO;
import com.example.kiemtra.entity.Category;
import com.example.kiemtra.entity.Product;

import java.util.List;

public interface CategoryService {

    Category findCategoryById(Long categoryId);

    List<Category> getAllCategory();

    List<Product> getListProductByCategoryId(Long categoryId);

    void createNewCategory(CategoryDTO categoryDTO);
}
