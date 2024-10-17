package com.wipro.hma.ms.service;

import com.wipro.hma.ms.dto.MealDTO;
import com.wipro.hma.ms.dto.MealPartnerVO;

import java.util.List;


public interface MealService {
    MealDTO createMeal(Long partnerId, MealDTO mealDTO);
    MealDTO updateMeal(Long mealId, MealDTO mealDTO);
    MealDTO getMealById(Long mealId);
    void deleteMeal(Long mealId);
    List<MealDTO> getAllMeals();
    List<MealDTO> getAllMealsByPartnerId(Long partnerId);

    MealPartnerVO getMealPartner (Long mealId);

}
