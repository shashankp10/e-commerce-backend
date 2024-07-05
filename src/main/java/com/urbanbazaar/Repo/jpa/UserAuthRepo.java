package com.urbanbazaar.Repo.jpa;

import com.urbanbazaar.Entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepo extends JpaRepository<UserAuth, String> {
    Optional<UserAuth> findByEmail(String email);
    boolean existsByEmail(String email);
}
