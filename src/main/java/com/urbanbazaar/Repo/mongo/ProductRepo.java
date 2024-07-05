package com.urbanbazaar.Repo.mongo;

import com.urbanbazaar.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);
    List<Product> findByBrandAndSubcategory(String brand, String subcategory);

    List<Product> findBySubcategory(String subcategory);
}
