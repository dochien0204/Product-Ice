package com.example.kiemtra.service.Impl;

import com.example.kiemtra.dto.ProductDTO;
import com.example.kiemtra.entity.Cart;
import com.example.kiemtra.entity.Category;
import com.example.kiemtra.entity.Product;
import com.example.kiemtra.exception.DuplicateException;
import com.example.kiemtra.exception.NotFoundException;
import com.example.kiemtra.repostitory.CartRepository;
import com.example.kiemtra.repostitory.ProductRepository;
import com.example.kiemtra.service.CategoryService;
import com.example.kiemtra.service.ProductService;
import com.example.kiemtra.util.ObjectUtil;
import com.github.slugify.Slugify;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Slugify slugify;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new NotFoundException("Không tìm thấy thông tin chi tiết của sản phẩm này");
        return product;
    }

    @Override
    public void createNewProductByCategory(Long categoryId, ProductDTO productDTO) {
        Category category1 = categoryService.findCategoryById(categoryId);
        Product product = new Product();
        System.out.println(productDTO.getSKU());
        if (checkProductExists(productDTO.getSKU()))
            throw new DuplicateException("Không thể tạo vì mã SKU đã tồn tại");
        product.setCategory(category1);
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setDescribe(productDTO.getDescribe());
        product.setImage(productDTO.getImage());
        product.setPurchases(productDTO.getPurchases());
        product.setSlug(slugify.slugify(productDTO.getProductName()));
        product.setSKU(productDTO.getSKU());
        productRepository.save(product);
    }

    @Override
    @ApiOperation(value = "Tìm kiếm sản phẩm theo 1 chuỗi nhỏ", response = ResponseEntity.class)
    public List<Product> findProductBySubString(String string) {
        List<Product> productList = productRepository.findBySlugContaining(slugify.slugify(string));
        if(productList.isEmpty())
                throw new NotFoundException("Cannot find product","Không tìm thấy sản phẩm nào", HttpStatus.NOT_FOUND);
        return productList;
    }

    @Override
    public List<Product> findPopularProducts(Integer number) {
        List<Product> productList = productRepository.findByPurchasesGreaterThanEqual(number);
        if(productList.isEmpty())
            throw new NotFoundException("Cannot find popular product","Không tìm thấy sản phẩm phổ biến nào",HttpStatus.NOT_FOUND);
        return productList;
    }

    @Override
    public void deleteProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new NotFoundException("Cannot find product by id","Không tìm thấy sản phẩm cần xóa",HttpStatus.NOT_FOUND);
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProductById(Long productId, ProductDTO productDTO) {
            Product product = getProductById(productId);
            if(checkProductExists(productDTO.getSKU()))
                throw new DuplicateException("Không thể thay đổi mã SKU này vì đã tồn tại");
            product = ObjectUtil.convertProduct(product,productDTO);
        return product ;
    }

    @Override
    public void addProductIntoCart(Long productId, Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Set<Product> products = cart.get().getProducts();
        Optional<Product> product = productRepository.findById(productId);
        products.add(product.get());
        cart.get().setProducts(products);
        cartRepository.save(cart.get());
        System.out.println("Gello");
    }

    @Override
    public void removeProductfromCart(Long productId, Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Set<Product> products = cart.get().getProducts();
        products.remove(productRepository.findById(productId));
        System.out.println(products.remove(productRepository.findById(productId)));
        cart.get().setProducts(products);
        cartRepository.save(cart.get());
    }

    public Product getProductById(Long productId)
    {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new NotFoundException("Không tìm thấy sản phẩm theo id " + productId);
        return product.get();
    }

    public boolean checkProductExists(String SKU)
    {
        Optional<Product> product = Optional.ofNullable(productRepository.findBySKU(SKU));
        return product.isPresent();
    }
}
