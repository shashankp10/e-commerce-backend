package com.urbanbazaar.Entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
public class Product {
    @Id
    private String id;
    private String title;
    private HashMap<String,String> description;
    private String category;
    private String subcategory;
    private String brand;
    private BigDecimal price;
    private long quantity;
    private boolean isAvailable;
    private List<String> url;
    private List<Reviews> reviews;
    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
