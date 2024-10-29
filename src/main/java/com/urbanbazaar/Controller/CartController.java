package com.urbanbazaar.Controller;


import com.urbanbazaar.DTO.CartDto;
import com.urbanbazaar.DTO.UserAuthDto;
import com.urbanbazaar.Entity.Cart;
import com.urbanbazaar.Entity.Product;
import com.urbanbazaar.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/items")
    public ResponseEntity<List<CartDto>> getAllItems() {
        List<CartDto> items = cartService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) {
        try {
            cartService.deleteItem(id);
            return ResponseEntity.ok("Item deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
