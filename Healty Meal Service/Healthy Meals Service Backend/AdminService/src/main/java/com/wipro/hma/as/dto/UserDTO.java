package com.wipro.hma.as.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;

    private String username;

    private String userEmail;

    private String phoneNumber;

    private String password;

    private String role;

    private LocalDateTime createdAt;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private Long mealId;

    private Long orderId;
}
