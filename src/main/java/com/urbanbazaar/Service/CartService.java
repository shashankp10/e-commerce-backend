package com.urbanbazaar.Service;

import com.urbanbazaar.DTO.CartDto;
import com.urbanbazaar.DTO.ProductDto;
import com.urbanbazaar.Entity.Cart;
import com.urbanbazaar.Entity.Product;

import java.util.List;

public interface CartService {
    CartDto addItem(CartDto cartItemDto);
    void deleteItem(String id);
    List<CartDto> getAllItems();
}
