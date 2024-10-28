package com.urbanbazaar.Service.Impl;

import com.urbanbazaar.DTO.UserAuthDto;
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
//        if (userRepo.existsByEmail(userDto.getEmail())) {
//            throw new EmailAlreadyExistsException("Email already exists");
//        }
        UserAuth user = new UserAuth();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        userRepo.save(user);
    }

    @Override
    public UserAuthDto updateUser(UserAuthDto userDto, String userId) {
        Optional<UserAuth> optionalUser = userRepo.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }

        UserAuth user = optionalUser.get();
        user.setEmail(user.getEmail());
        user.setName(user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(user.getRoles());
        UserAuth updatedUser = userRepo.save(user);

        return convertUserToDto(updatedUser);
    }

    @Override
    public UserAuthDto getUserById(String userId) {
        Optional<UserAuth> optionalUser = userRepo.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }

        return convertUserToDto(optionalUser.get());
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
        UserAuthDto dto = this.modelMapper.map(user, UserAuthDto.class);
        return dto;
    }
}
