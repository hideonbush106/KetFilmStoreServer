package com.sukute1712.ketfilmstore.repository;

import com.sukute1712.ketfilmstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
