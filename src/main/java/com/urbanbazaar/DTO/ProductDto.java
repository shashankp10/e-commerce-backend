package com.urbanbazaar.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
public class ProductDto {
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
//    private List<Reviews> reviews;
}
