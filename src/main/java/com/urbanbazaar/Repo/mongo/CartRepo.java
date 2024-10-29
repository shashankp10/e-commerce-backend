package com.urbanbazaar.Repo.mongo;

import com.urbanbazaar.Entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepo extends MongoRepository<Cart, String> {
//    Optional<Cart> findByUserId(String userId);
}
