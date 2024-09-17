package com.urbanbazaar.Service.Impl;

import com.urbanbazaar.DTO.ProductDto;
import com.urbanbazaar.DTO.UserAuthDto;
import com.urbanbazaar.Entity.Product;
import com.urbanbazaar.Repo.mongo.ProductRepo;
import com.urbanbazaar.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = convertDtoToEntity(productDto);
        product = productRepo.save(product);
        return convertEntityToDto(product);
    }

    @Override
    public ProductDto updateUser(ProductDto productDto, String productId) {
        return productRepo.findById(productId)
                .map(existingProduct -> {
                    modelMapper.map(productDto, existingProduct);
                    Product updatedProduct = productRepo.save(existingProduct);
                    return convertEntityToDto(updatedProduct);
                })
                .orElse(null);
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
        ProductDto dto = this.modelMapper.map(product, ProductDto.class);
        return dto;
    }

    private Product convertDtoToEntity(ProductDto dto) {
        Product product = this.modelMapper.map(dto, Product.class);
        return product;
    }
}
