package com.wipro.hma.rs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(nullable = false)
    private String userId;      // ID of the user giving the rating

    @Column(nullable = false)
    private String partnerId;   // ID of the partner being rated

    @Column(nullable = false)
    private int rating;         // Rating value

    private String feedback;    // User's feedback (optional)

    @CreationTimestamp
    private LocalDateTime createdAt;
}
