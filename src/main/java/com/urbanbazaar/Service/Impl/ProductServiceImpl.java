package com.urbanbazaar.Service.Impl;

import com.urbanbazaar.DTO.ProductDto;
import com.urbanbazaar.Entity.Product;
import com.urbanbazaar.Repo.mongo.ProductRepo;
import com.urbanbazaar.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = convertDtoToEntity(productDto);
        product = productRepo.save(product);
        return convertEntityToDto(product);
    }

    @Override
    public ProductDto updateUser(ProductDto productDto, String productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (!optionalProduct.isPresent()) {
            return null;
        }

        Product product = optionalProduct.get();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setSubcategory(productDto.getSubcategory());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setAvailable(productDto.isAvailable());
        product.setUrl(productDto.getUrl());
        product.setReviews(productDto.getReviews());

        product = productRepo.save(product);
        return convertEntityToDto(product);
    }
    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    @Override
    public ProductDto getProductById(String productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        return optionalProduct.map(this::convertEntityToDto).orElse(null);
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        List<Product> products = productRepo.findByCategory(category);
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByBrand(String brand) {
        List<Product> products = productRepo.findByBrand(brand);
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsBySubcategory(String subcategory) {
        List<Product> products = productRepo.findBySubcategory(subcategory);
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByBrandAndCategory(String brand, String subcategory) {
        List<Product> products = productRepo.findByBrandAndSubcategory(brand, subcategory);
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(String productId) {
        productRepo.deleteById(productId);
    }

    private ProductDto convertEntityToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setSubcategory(product.getSubcategory());
        dto.setBrand(product.getBrand());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setAvailable(product.isAvailable());
        dto.setUrl(product.getUrl());
        dto.setReviews(product.getReviews());
        return dto;
    }

    private Product convertDtoToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setSubcategory(dto.getSubcategory());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setAvailable(dto.isAvailable());
        product.setUrl(dto.getUrl());
        product.setReviews(dto.getReviews());
        return product;
    }
}
