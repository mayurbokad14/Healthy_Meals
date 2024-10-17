package com.wipro.hma.ms.controller;

import com.wipro.hma.ms.dto.MealDTO;
import com.wipro.hma.ms.dto.MealPartnerVO;
import com.wipro.hma.ms.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin(origins = "http://localhost:4200")
public class MealController {
    @Autowired
    private MealService mealService;

    @PostMapping("/add/{partnerId}")
    public ResponseEntity<MealDTO> createMeal(@PathVariable Long partnerId, @RequestBody MealDTO mealDTO) {
        MealDTO createdMeal = mealService.createMeal(partnerId, mealDTO);
        return new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
    }

    @PutMapping("/{mealId}")
    public ResponseEntity<MealDTO> updateMeal(@PathVariable Long mealId, @RequestBody MealDTO mealDTO) {
        MealDTO updatedMeal = mealService.updateMeal(mealId, mealDTO);
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<MealDTO> getMealById(@PathVariable Long mealId) {
        MealDTO mealDTO = mealService.getMealById(mealId);
        return new ResponseEntity<>(mealDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long mealId) {
        mealService.deleteMeal(mealId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MealDTO>> getAllMeals() {
        List<MealDTO> meals = mealService.getAllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @GetMapping("/partner/{partnerId}")
    public ResponseEntity<List<MealDTO>> getAllMealsByPartnerId(@PathVariable Long partnerId) {
        List<MealDTO> meals = mealService.getAllMealsByPartnerId(partnerId);
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @GetMapping("/meal-partner/{mealId}")
    public ResponseEntity<MealPartnerVO> getMealPartner(@PathVariable Long mealId){
        MealPartnerVO mealPartnerVO = mealService.getMealPartner(mealId);
        return new ResponseEntity<>(mealPartnerVO, HttpStatus.OK);
    }
}
