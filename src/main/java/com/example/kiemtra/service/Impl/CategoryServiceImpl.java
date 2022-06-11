package com.example.kiemtra.service.Impl;

import com.example.kiemtra.dto.CategoryDTO;
import com.example.kiemtra.entity.Category;
import com.example.kiemtra.entity.Product;
import com.example.kiemtra.exception.DuplicateException;
import com.example.kiemtra.exception.NotFoundException;
import com.example.kiemtra.repostitory.CategoryRepository;
import com.example.kiemtra.repostitory.ProductRepository;
import com.example.kiemtra.service.CategoryService;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Slugify slugify;

    @Override
    public Category findCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new NotFoundException("Cannot find Category by id " + categoryId);
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getListProductByCategoryId(Long categoryId) {
        List<Product> productList = new ArrayList<>();
        Optional<Category> category = Optional.of(new Category());
        for(Product product : categoryRepository.findById(categoryId).get().getProductList()){
            productList.add(product);
        }
        return productList;
    }

    @Override
    public void createNewCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        if(checkCategoryExists(categoryDTO.getCategoryName()))
            throw new DuplicateException("Không thể tạo");
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryImage(categoryDTO.getCategoryImage());
        category.setSlug(slugify.slugify(categoryDTO.getCategoryName()));
        categoryRepository.save(category);

    }

    public  boolean checkCategoryExists(String categoryName)
    {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByCategoryName(categoryName));
        return category.isPresent();
    }


}
