package com.wipro.hma.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long cartId;
    private Long userId;
    private List<Long> mealIds;
    private BigDecimal totalPrice;
    // List to hold detailed meal information
    private List<MealDTO> meals;
}
