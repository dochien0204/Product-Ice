package com.example.kiemtra.controller;


import com.example.kiemtra.base.VsResponseUtil;
import com.example.kiemtra.dto.ProductDTO;
import com.example.kiemtra.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation(value = "Lấy ra tất cả sản phẩm", response = ResponseEntity.class)
    public ResponseEntity<?> getAllProduct()
    {
        return VsResponseUtil.ok(productService.getAllProduct());
    }

    @GetMapping("/{productId}")
    @ApiOperation(value = "Lấy ra danh sách sản phẩm dựa trên id của loại sản phẩm", response = ResponseEntity.class)
    public ResponseEntity<?> getProductByCategoryId(@PathVariable(name = "productId") Long productId)
    {
        return VsResponseUtil.ok(productService.findProductById(productId));
    }

    @PostMapping("/add-product/{categoryId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Thêm sản phẩm theo loại sản phẩm", response = ResponseEntity.class)
    public ResponseEntity<?> createNewProductByCategory(@PathVariable(name = "categoryId") Long categoryId,
                                                        @RequestBody ProductDTO productDTO)
    {
        productService.createNewProductByCategory(categoryId,productDTO);
        return ResponseEntity.status(200).body("Tạo thành công");
    }

    @GetMapping("/sub-string")
    @ApiOperation(value = "Tìm kiếm sản phẩm theo 1 chuỗi nhỏ", response = ResponseEntity.class)
    public ResponseEntity<?> findProductBySubString(@RequestParam(name = "string") String string)
    {

        return VsResponseUtil.ok(productService.findProductBySubString(string));
    }

    @GetMapping("/popular/{purchases}")
    @ApiOperation(value = "Tìm kiếm sản phẩm theo lượt mua nhiều")
    public ResponseEntity<?> findPopularProducts(@PathVariable(name = "purchases") Integer number)
    {
        return VsResponseUtil.ok(productService.findPopularProducts(number));
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Xóa sản phẩm theo id sản phẩm", response = ResponseEntity.class)
    public ResponseEntity<?> deleteProductById(@PathVariable(name = "productId") Long productId)
    {
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Xóa thành công");
    }

    @PatchMapping("/update/{productId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Sửa thông tin sản phẩm theo id sản phẩm")
    public ResponseEntity<?> updateProdudtById(@PathVariable(name = "productId") Long productId,
                                               @RequestBody ProductDTO productDTO)
    {
        productService.updateProductById(productId,productDTO);
        return ResponseEntity.status(200).body("Sửa thông tin sản phẩm thành công");
    }


    @PatchMapping("/add-product-into-cart/{productId}/{cartId}")
    @ApiOperation(value = "Thêm sản phẩm vào giỏ hàng !", response = ResponseEntity.class)
    public ResponseEntity<?> addProductIntoCart(@PathVariable Long productId,
                                                @PathVariable Long cartId)
    {
        productService.addProductIntoCart(productId,cartId);
        return ResponseEntity.status(200).body("Thêm vào giỏ hàng thành công !");
    }

    @PatchMapping("/delete-product-from-cart/{productId}/{cartId}")
    @ApiOperation(value = "Xóa sản phẩm khỏi giỏ hàng", response = ResponseEntity.class)
    public ResponseEntity<?> deleteProductFromCart(@PathVariable Long productId,
                                                   @PathVariable Long cartId)
    {
        productService.removeProductfromCart(productId, cartId);
        return ResponseEntity.status(200).body("Xóa sản phẩm khỏi giỏ hàng thành công");
    }
}
