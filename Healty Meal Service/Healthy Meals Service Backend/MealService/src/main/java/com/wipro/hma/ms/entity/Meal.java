package com.wipro.hma.ms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId; // Primary key

    @Column(nullable = false)
    private String mealName; // Name of the meal

    @Column(nullable = false)
    private String description; // Description of the meal

    @Column(nullable = false)
    private Double price; // Price of the meal

    @Column(nullable = false)
    private String category; // Category (e.g., vegetarian, non-vegetarian)

    @Column(length = 65555)
    private String imageUrl; // URL for the meal image

    @Column(nullable = false)
    private Long partnerId;

    private boolean available;

    private int mealQuantity;
}
