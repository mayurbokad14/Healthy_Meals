package com.wipro.hma.ps.controller;

import com.wipro.hma.ps.dto.MealDTO;
import com.wipro.hma.ps.dto.PartnerDTO;
import com.wipro.hma.ps.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
//@CrossOrigin("http://localhost:4200")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @PostMapping("/register")
    public ResponseEntity<PartnerDTO> registerPartner(@RequestBody PartnerDTO partnerDTO) {
        PartnerDTO createdPartner = partnerService.registerPartner(partnerDTO);
        return new ResponseEntity<>(createdPartner, HttpStatus.CREATED);
    }

    @PutMapping("/{partnerId}")
    public ResponseEntity<PartnerDTO> updatePartner(@PathVariable Long partnerId, @RequestBody PartnerDTO partnerDTO) {
        PartnerDTO updatedPartner = partnerService.updatePartner(partnerId, partnerDTO);
        return new ResponseEntity<>(updatedPartner, HttpStatus.OK);
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity<PartnerDTO> getPartnerById(@PathVariable Long partnerId) {
        PartnerDTO partnerDTO = partnerService.getPartnerById(partnerId);
        return new ResponseEntity<>(partnerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{partnerId}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long partnerId) {
        partnerService.deletePartner(partnerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PartnerDTO>> getAllPartners() {
        List<PartnerDTO> partners = partnerService.getAllPartners();
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }

/********************************/
    @PostMapping("/meals/add/{partnerId}")
    public ResponseEntity<MealDTO> addMeal(@PathVariable Long partnerId, @RequestBody MealDTO mealDTO) {
        MealDTO createdMeal = partnerService.addMeal(partnerId, mealDTO);
        return new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
    }

    @PutMapping("/meals/update/{mealId}")
    public ResponseEntity<MealDTO> updateMeal(@PathVariable Long mealId, @RequestBody MealDTO mealDTO) {
        MealDTO updatedMeal = partnerService.updateMeal(mealId, mealDTO);
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }

    @GetMapping("/meals/{mealId}")
    public ResponseEntity<MealDTO> getMealById(@PathVariable Long mealId) {
        MealDTO mealDTO = partnerService.getMealById(mealId);
        return new ResponseEntity<>(mealDTO, HttpStatus.OK);
    }

    @DeleteMapping("/meals/delete/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long mealId) {
        partnerService.deleteMeal(mealId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/meals/all")
    public ResponseEntity<List<MealDTO>> getAllMeals() {
        List<MealDTO> meals = partnerService.getAllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @GetMapping("/{partnerId}/meals")
    public ResponseEntity<List<MealDTO>> getMealsByPartnerId(@PathVariable Long partnerId) {
        List<MealDTO> meals = partnerService.getMealsByPartnerId(partnerId);
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

}
