package com.urbanbazaar.Repo.mongo;

import com.urbanbazaar.Entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepo extends MongoRepository<Cart, String> {

}
