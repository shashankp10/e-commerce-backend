package com.urbanbazaar.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cart")
public class Cart {
    @Id
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private String url;
    private LocalDateTime timestamp;
}
