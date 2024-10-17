package com.wipro.hma.ps.service;

import com.wipro.hma.ps.dto.MealDTO;
import com.wipro.hma.ps.dto.PartnerDTO;

import java.util.List;

public interface PartnerService {
    PartnerDTO registerPartner(PartnerDTO partnerDTO);
    PartnerDTO updatePartner(Long partnerId, PartnerDTO partnerDTO);
    PartnerDTO getPartnerById(Long partnerId);
    void deletePartner(Long partnerId);
    List<PartnerDTO> getAllPartners();
    PartnerDTO getUserByUsername(String username);

    MealDTO addMeal(Long partnerId, MealDTO mealDTO);
    MealDTO updateMeal(Long mealId, MealDTO mealDTO);
    MealDTO getMealById(Long mealId);
    void deleteMeal(Long mealId);
    List<MealDTO> getAllMeals();
    List<MealDTO> getMealsByPartnerId(Long partnerId);
}
