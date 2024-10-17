package com.wipro.hma.ms.service;

import com.wipro.hma.ms.dto.MealDTO;
import com.wipro.hma.ms.dto.MealPartnerVO;
import com.wipro.hma.ms.dto.PartnerDTO;
import com.wipro.hma.ms.entity.Meal;
import com.wipro.hma.ms.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService{

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    RestTemplate restTemplate;

    private final String PARTNER_SERVICE_URL = "http://localhost:8084/private/partners";

    @Override
    public MealDTO createMeal(Long partnerId, MealDTO mealDTO) {
        Meal meal = new Meal();
        meal.setMealName(mealDTO.getMealName());
        meal.setDescription(mealDTO.getDescription());
        meal.setPrice(mealDTO.getPrice());
        meal.setCategory(mealDTO.getCategory());
        meal.setImageUrl(mealDTO.getImageUrl());
        meal.setPartnerId(partnerId);
        meal.setAvailable(mealDTO.isAvailable());
        meal = mealRepository.save(meal);
        return convertToDTO(meal);
    }

    @Override
    public MealDTO updateMeal(Long mealId, MealDTO mealDTO) {
        Meal existingMeal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));

        existingMeal.setMealName(mealDTO.getMealName());
        existingMeal.setDescription(mealDTO.getDescription());
        existingMeal.setPrice(mealDTO.getPrice());
        existingMeal.setCategory(mealDTO.getCategory());
        existingMeal.setImageUrl(mealDTO.getImageUrl());
        existingMeal.setAvailable(mealDTO.isAvailable());
        mealRepository.save(existingMeal);
        return convertToDTO(existingMeal);
    }

    @Override
    public MealDTO getMealById(Long mealId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        return convertToDTO(meal);
    }

    @Override
    public void deleteMeal(Long mealId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
        mealRepository.delete(meal);
    }

    @Override
    public List<MealDTO> getAllMeals() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MealDTO> getAllMealsByPartnerId(Long partnerId) {
        List<Meal> meals = mealRepository.findByPartnerId(partnerId);
        // Convert Meal entities to MealDTOs
        return meals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MealPartnerVO getMealPartner (Long mealId){
        MealDTO mealDTO = getMealById(mealId);

        Long partnerId =mealDTO.getPartnerId();

        PartnerDTO partnerDTO = restTemplate.getForObject(PARTNER_SERVICE_URL + "/" + partnerId, PartnerDTO.class);

        MealPartnerVO mealPartnerVO = new MealPartnerVO();

        mealPartnerVO.setMealDTO(mealDTO);

        mealPartnerVO.setPartnerDTO(partnerDTO);

        return mealPartnerVO;
    }



    private MealDTO convertToDTO(Meal meal) {
        MealDTO dto = new MealDTO();
        dto.setMealId(meal.getMealId());
        dto.setMealName(meal.getMealName());
        dto.setDescription(meal.getDescription());
        dto.setPrice(meal.getPrice());
        dto.setCategory(meal.getCategory());
        dto.setImageUrl(meal.getImageUrl());
        dto.setPartnerId(meal.getPartnerId());
        dto.setAvailable(meal.isAvailable());
        return dto;
    }
}
