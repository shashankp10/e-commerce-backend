package com.urbanbazaar.Service;

import com.urbanbazaar.DTO.CartDto;

public interface CartService {
    CartDto addItem(CartDto cartItemDto);
    void deleteItem(String id);
}
