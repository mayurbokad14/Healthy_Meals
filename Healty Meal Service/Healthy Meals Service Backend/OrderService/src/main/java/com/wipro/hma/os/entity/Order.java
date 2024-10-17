package com.wipro.hma.os.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;           // Primary key

    @Column(nullable = false)
    private Long userId;            // User ID of the person who made the order

    @Column(nullable = false)
    private Long partnerId;         // Partner ID to whom the order is assigned

    @ElementCollection
    private List<Long> mealIds;     // List of meal IDs included in the order

    @Column(nullable = false)
    private double totalPrice;      // Total price of the order

    @Column(nullable = false)
    private LocalDateTime createdAt; // Order creation timestamp

}
