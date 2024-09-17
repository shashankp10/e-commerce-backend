package com.urbanbazaar.Service.Impl;

import com.urbanbazaar.DTO.CartDto;
import com.urbanbazaar.DTO.ProductDto;
import com.urbanbazaar.Entity.Cart;
import com.urbanbazaar.Entity.Product;
import com.urbanbazaar.Repo.mongo.CartRepo;
import com.urbanbazaar.Service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDto addItem(CartDto cartItemDto) {
        Cart cart = convertDtoToEntity(cartItemDto);
        cart = cartRepo.save(cart);
        return convertEntityToDto(cart);
    }

    @Override
    public void deleteItem(String id) {
        Optional<Cart> optionalCart = cartRepo.findById(id);
        if (optionalCart.isPresent()) {
            cartRepo.deleteById(id);
        } else {
            throw new RuntimeException("Item with id " + id + " not found in the cart");
        }
    }

    private CartDto convertEntityToDto(Cart cart) {
        CartDto dto = this.modelMapper.map(cart, CartDto.class);
        return dto;
    }
    private Cart convertDtoToEntity(CartDto dto) {
        Cart cart = this.modelMapper.map(dto, Cart.class);
        return cart;
    }
}
