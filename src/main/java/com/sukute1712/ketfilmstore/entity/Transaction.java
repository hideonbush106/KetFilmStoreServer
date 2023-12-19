package com.sukute1712.ketfilmstore.entity;

import com.sukute1712.ketfilmstore.utils.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends AbstractAuditable<User, UUID> {

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    @Column(nullable = false)
    private OrderStatus status;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", unique = true)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
