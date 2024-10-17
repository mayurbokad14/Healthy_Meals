package com.wipro.hma.os.dto;

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
public class OrderDTO {

    private Long orderId;

    private Long userId;

    private Long partnerId;         // Partner ID to whom the order is assigned

    private List<Long> mealIds;     // List of meal IDs included in the order

    private double totalPrice;

    private LocalDateTime createdAt;
}
