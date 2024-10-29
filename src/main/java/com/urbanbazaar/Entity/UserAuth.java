package com.urbanbazaar.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "user")
public class UserAuth {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String phone;
    private String roles;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
