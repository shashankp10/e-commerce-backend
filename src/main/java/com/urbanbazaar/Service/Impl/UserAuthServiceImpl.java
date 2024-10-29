package com.urbanbazaar.Service.Impl;

import com.urbanbazaar.DTO.UserAuthDto;
import com.urbanbazaar.Entity.Cart;
import com.urbanbazaar.Entity.UserAuth;
import com.urbanbazaar.Repo.jpa.UserAuthRepo;
import com.urbanbazaar.Service.UserAuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserAuthRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createUser(UserAuthDto userDto) {
        // Uncomment and implement exception handling if needed
        // if (userRepo.existsByEmail(userDto.getEmail())) {
        //     throw new EmailAlreadyExistsException("Email already exists");
        // }
        UserAuth user = convertDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepo.save(user);
    }

    @Override
    public UserAuthDto getUserById(String userId) {
        Optional<UserAuth> optionalUser = userRepo.findById(userId);
        return optionalUser.map(this::convertUserToDto).orElse(null);
    }

    @Override
    public UserAuthDto updateUser(String userId, UserAuthDto userDto) {
        Optional<UserAuth> optionalUser = userRepo.findById(userId);

        if (!optionalUser.isPresent()) {
            return null;
        }

        UserAuth user = optionalUser.get();

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getGender() != null) {
            user.setGender(userDto.getGender());
        }
        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }
        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }

        userRepo.save(user);

        return convertUserToDto(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        Optional<UserAuth> optionalUser = userRepo.findByEmail(email);
        if (!optionalUser.isPresent()) {
            return false;
        }

        UserAuth user = optionalUser.get();
        return passwordEncoder.matches(password, user.getPassword());
    }

    private UserAuthDto convertUserToDto(UserAuth user) {
        return modelMapper.map(user, UserAuthDto.class);
    }
    private UserAuth convertDtoToUser(UserAuthDto userDto) {
        return modelMapper.map(userDto, UserAuth.class);
    }
    public String findUserIdByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(UserAuth::getId)
                .orElse(null);
    }


}
