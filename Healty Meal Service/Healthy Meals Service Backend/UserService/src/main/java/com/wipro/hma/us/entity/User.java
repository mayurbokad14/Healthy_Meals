package com.wipro.hma.us.entity;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userEmail;

    private String phoneNumber;

    @Column(nullable = false)
    private String password;


    private String role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String address;

    private String city;

    private String state;

    private String zipCode;

}
