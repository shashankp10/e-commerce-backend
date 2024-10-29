package com.urbanbazaar.DTO;

import com.urbanbazaar.Entity.Product;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CartDto {
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private String url;
    private LocalDateTime timestamp;
}
