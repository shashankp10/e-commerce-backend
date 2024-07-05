package com.urbanbazaar.Service;

import com.urbanbazaar.DTO.UserAuthDto;

public interface UserAuthService {
    void createUser(UserAuthDto customerDto);
    UserAuthDto updateUser(UserAuthDto customer, String userId);
    UserAuthDto getUserById(String userId);
    void deleteUser(String userId);
    boolean authenticateUser(String email, String password);
}
