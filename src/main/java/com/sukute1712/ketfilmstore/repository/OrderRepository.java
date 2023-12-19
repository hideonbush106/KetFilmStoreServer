package com.sukute1712.ketfilmstore.repository;

import com.sukute1712.ketfilmstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
