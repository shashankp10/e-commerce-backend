package com.urbanbazaar.Controller;

import com.urbanbazaar.DTO.JWTResponse;
import com.urbanbazaar.DTO.LoginRequest;
import com.urbanbazaar.DTO.UserAuthDto;
import com.urbanbazaar.Security.JWTTokenUtil;
import com.urbanbazaar.Service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("urbanbazaar/api/v1")
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
}
