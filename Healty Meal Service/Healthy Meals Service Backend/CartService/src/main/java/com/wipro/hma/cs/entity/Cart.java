package com.wipro.hma.cs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(nullable = false)
    private Long userId; // Reference to the user who owns this cart

    @ElementCollection
    private List<Long> mealIds = new ArrayList<>(); // List of meal IDs added to the cart

    @Column(nullable = false)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @CreationTimestamp
    private LocalDateTime createdAt;
}