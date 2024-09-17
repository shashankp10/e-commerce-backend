package com.urbanbazaar.Controller;


import com.urbanbazaar.DTO.CartDto;
import com.urbanbazaar.DTO.UserAuthDto;
import com.urbanbazaar.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("urbanbazaar/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartItemDto){
        cartService.addItem(cartItemDto);
        return ResponseEntity.ok("Item added to cart");
    }
}
