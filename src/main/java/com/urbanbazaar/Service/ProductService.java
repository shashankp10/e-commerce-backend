package com.urbanbazaar.Service;

import com.urbanbazaar.DTO.ProductDto;
import com.urbanbazaar.DTO.UserAuthDto;
import com.urbanbazaar.Entity.Product;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateUser(ProductDto customer, String productId);
    ProductDto getProductById(String productId);

    List<ProductDto> getProductsByCategory(String category);

    List<ProductDto> getProductsByBrand(String brand);
    List<ProductDto> getProducts();
    List<ProductDto> getProductsBySubcategory(String subcategory);
    List<ProductDto> getProductsByBrandAndCategory(String brand, String subcategory);
    void deleteProduct(String userId);
}
