package com.wipro.hma.as.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;

    private Long userId;

    private Long mealId;

    private int quantity;

    private double totalPrice;

    private LocalDateTime createdAt;
}
