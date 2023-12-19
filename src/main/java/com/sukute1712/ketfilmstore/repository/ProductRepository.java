package com.sukute1712.ketfilmstore.repository;

import com.sukute1712.ketfilmstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByName(String name);
}
