package com.urbanbazaar.Controller;

import com.urbanbazaar.DTO.ProductDto;
import com.urbanbazaar.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("urbanbazaar/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @PostMapping("/add")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.ok(createdProduct);
    }

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String id) {
        ProductDto updatedProduct = productService.updateUser(productDto, id);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_SELLER')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        ProductDto product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_SELLER')")
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_SELLER')")
    @GetMapping("/subcategory/{subcategory}")
    public ResponseEntity<List<ProductDto>> getProductsBySubCategory(@PathVariable String subcategory) {
        List<ProductDto> products = productService.getProductsBySubcategory(subcategory);
        return ResponseEntity.ok(products);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_SELLER')")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String category) {
        List<ProductDto> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_SELLER')")
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<ProductDto>> getProductsByBrand(@PathVariable String brand) {
        List<ProductDto> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_SELLER')")
    @GetMapping("/brand/{brand}/subcategory/{subcategory}")
    public ResponseEntity<List<ProductDto>> getProductsByBrandAndCategory(@PathVariable String brand, @PathVariable String subcategory) {
        List<ProductDto> products = productService.getProductsByBrandAndCategory(brand, subcategory);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Item deleted successfully");
    }
}
