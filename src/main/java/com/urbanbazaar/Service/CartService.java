package com.urbanbazaar.Service;

import com.urbanbazaar.DTO.CartDto;

import java.util.List;

public interface CartService {
    CartDto addItem(CartDto cartItemDto);
    void deleteItem(String id);
    List<CartDto> getAllItems();
}
