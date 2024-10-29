package com.urbanbazaar.Controller;

import com.urbanbazaar.DTO.CartDto;
import com.urbanbazaar.DTO.JWTResponse;
import com.urbanbazaar.DTO.LoginRequest;
import com.urbanbazaar.DTO.UserAuthDto;
import com.urbanbazaar.Security.JWTTokenUtil;
import com.urbanbazaar.Service.UserAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("urbanbazaar/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserAuthService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody UserAuthDto registrationDto) {
        userService.createUser(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
            return ResponseEntity.ok(new JWTResponse(jwt));
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Invalid email or password") {};
        }
    }
    @PatchMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<UserAuthDto> updateUserFields(
            @PathVariable String userId,
            @RequestBody UserAuthDto userDto) {

            UserAuthDto updatedUser = userService.updateUser(userId, userDto);
            return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<UserAuthDto> getUserById(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
             token = authorizationHeader.substring(7);
         }
        String email = jwtTokenUtil.extractEmail(token);
        String userId = userService.findUserIdByEmail(email);
        UserAuthDto userDto = userService.getUserById(userId);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(userDto);
    }
}
