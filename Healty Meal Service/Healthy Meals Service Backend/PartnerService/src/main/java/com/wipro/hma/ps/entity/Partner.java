package com.wipro.hma.ps.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;       // Primary key

    @Column(nullable = false)
    private String partnerName;    // Name of the partner

    @Column(nullable = false, unique = true)
    private String shopName;       // Name of the shop

    @Column(nullable = false, unique = true)
    private String partnerEmail;      // Partner's email address

    private String phoneNumber;

    @Column(nullable = false)
    private String password;       // Encrypted password for authentication

    private String role="PARTNER";

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String address;
    private String city;
    private String state;
    private String zipCode;

    private String shopDescription; // Description of the shop (optional)
    private String logoUrl;         // URL for the shop logo (optional)
    private List<Long> mealIds;     // List of meals offered by the partner

}
