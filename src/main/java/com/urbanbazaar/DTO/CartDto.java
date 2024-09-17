package com.urbanbazaar.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CartDto {
    private String id;
    private LocalDateTime timestamp;
}
