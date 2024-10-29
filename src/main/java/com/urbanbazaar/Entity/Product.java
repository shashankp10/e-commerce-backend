package com.urbanbazaar.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.*;

@Data
@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    private String product_id;
    private String title;
    private HashMap<String,String> description;
    private String category;
    private String subcategory;
    private String brand;
    private BigDecimal price;
    private long quantity;
    private boolean isAvailable;
    private List<String> url;

    @PrePersist
    protected void onCreate() {
        if (product_id == null) {
            product_id = UUID.randomUUID().toString();
        }
    }
}
