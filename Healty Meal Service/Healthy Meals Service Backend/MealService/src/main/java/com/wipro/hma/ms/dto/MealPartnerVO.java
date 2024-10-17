package com.wipro.hma.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealPartnerVO {
    private MealDTO mealDTO;
    private PartnerDTO partnerDTO;
}
