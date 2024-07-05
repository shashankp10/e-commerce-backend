package com.urbanbazaar.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewsDto {
    private String id;
    private String name;
    private double rating;
    private String feedback;
    private Date date;
}
