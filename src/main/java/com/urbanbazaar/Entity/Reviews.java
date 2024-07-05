package com.urbanbazaar.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Data
public class Reviews {
    @Id
    private String id;
    private String name;
    private double rating;
    private String feedback;
    private Date date;
    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        if (date == null) {
            date = getCurrentTimeWithTimeZone();
        }
    }

    private Date getCurrentTimeWithTimeZone() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
        return new Date(sdf.format(new Date()));
    }
}
