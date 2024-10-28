package com.urbanbazaar.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartDto {
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private String url;
    private LocalDateTime timestamp;
}
