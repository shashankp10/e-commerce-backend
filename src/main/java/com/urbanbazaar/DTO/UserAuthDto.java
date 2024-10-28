package com.urbanbazaar.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String roles;
}
