package com.wipro.hma.os.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {
    private Long mealId;
    private String mealName;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;
    private Long partnerId;
    private boolean available;
    private int mealQuantity;
}
