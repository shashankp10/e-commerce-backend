package com.urbanbazaar.Service;

import com.urbanbazaar.DTO.UserAuthDto;

public interface UserAuthService {
    void createUser(UserAuthDto customerDto);
    UserAuthDto getUserById(String userId);
    UserAuthDto updateUser(String userId, UserAuthDto userDto);
    void deleteUser(String userId);
    boolean authenticateUser(String email, String password);

    String findUserIdByEmail(String email);
}
