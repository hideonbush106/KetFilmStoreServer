package com.sukute1712.ketfilmstore.repository;

import com.sukute1712.ketfilmstore.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
