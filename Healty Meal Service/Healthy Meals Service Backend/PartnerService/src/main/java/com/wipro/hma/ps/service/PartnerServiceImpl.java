package com.wipro.hma.ps.service;

import com.wipro.hma.ps.dto.MealDTO;
import com.wipro.hma.ps.dto.PartnerDTO;
import com.wipro.hma.ps.entity.Partner;
import com.wipro.hma.ps.exception.PartnerNotFoundException;
import com.wipro.hma.ps.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerServiceImpl implements PartnerService{

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private RestTemplate restTemplate;

    // Meal Service URL
    private static final String MEAL_SERVICE_URL = "http://localhost:8082/api/meals";

    @Override
    public PartnerDTO registerPartner(PartnerDTO partnerDTO) {
        Partner partner = new Partner();
        partner.setPartnerName(partnerDTO.getPartnerName());
        partner.setShopName(partnerDTO.getShopName());
        partner.setPartnerEmail(partnerDTO.getPartnerEmail());
        partner.setPhoneNumber(partnerDTO.getPhoneNumber());
        partner.setPassword(passwordEncoder.encode(partnerDTO.getPassword()));
//        partner.setPassword(partnerDTO.getPassword());
        partner.setRole("PARTNER");
        partner.setCreatedAt(LocalDateTime.now());
        partner.setAddress(partnerDTO.getAddress());
        partner.setCity(partnerDTO.getCity());
        partner.setState(partnerDTO.getState());
        partner.setZipCode(partnerDTO.getZipCode());
        partner.setShopDescription(partnerDTO.getShopDescription());
        partner.setLogoUrl(partnerDTO.getLogoUrl());
        partner.setMealIds(partnerDTO.getMealIds());

        partner = partnerRepository.save(partner);
        return convertToDTO(partner);
    }

    @Override
    public PartnerDTO updatePartner(Long partnerId, PartnerDTO partnerDTO) {
        Partner existingPartner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new PartnerNotFoundException("Partner not found"));

        existingPartner.setPartnerName(partnerDTO.getPartnerName());
        existingPartner.setShopName(partnerDTO.getShopName());
        existingPartner.setPartnerEmail(partnerDTO.getPartnerEmail());
        existingPartner.setPhoneNumber(partnerDTO.getPhoneNumber());
        existingPartner.setPassword(partnerDTO.getPassword());
        existingPartner.setAddress(partnerDTO.getAddress());
        existingPartner.setCity(partnerDTO.getCity());
        existingPartner.setState(partnerDTO.getState());
        existingPartner.setZipCode(partnerDTO.getZipCode());
        existingPartner.setShopDescription(partnerDTO.getShopDescription());
        existingPartner.setLogoUrl(partnerDTO.getLogoUrl());
        existingPartner.setMealIds(partnerDTO.getMealIds());

        partnerRepository.save(existingPartner);
        return convertToDTO(existingPartner);
    }

    @Override
    public PartnerDTO getPartnerById(Long partnerId) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new PartnerNotFoundException("Partner not found"));
        return convertToDTO(partner);
    }

    @Override
    public List<PartnerDTO> getAllPartners() {
        List<Partner> partners = partnerRepository.findAll();
        return partners.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deletePartner(Long partnerId) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new PartnerNotFoundException("Partner not found"));
        partnerRepository.delete(partner);
    }

    @Override
    public PartnerDTO getUserByUsername(String username) {
        Partner user = partnerRepository.findByPartnerName(username).orElse(null);  // Assuming you're fetching the User entity by username
        return convertToDTO(user);  // Convert User entity to UserDTO
    }


    //Calling Meal Service Operations

    @Override
    public MealDTO addMeal(Long partnerId, MealDTO mealDTO) {
        ResponseEntity<MealDTO> response = restTemplate.postForEntity(MEAL_SERVICE_URL + "/add/" + partnerId, mealDTO, MealDTO.class);
        return response.getBody();
    }

    @Override
    public MealDTO updateMeal(Long mealId, MealDTO mealDTO) {
        restTemplate.put(MEAL_SERVICE_URL + "/update/" + mealId, mealDTO);
        return mealDTO;
    }

    @Override
    public MealDTO getMealById(Long mealId) {
        return restTemplate.getForObject(MEAL_SERVICE_URL + "/" + mealId, MealDTO.class);
    }

    @Override
    public void deleteMeal(Long mealId) {
        restTemplate.delete(MEAL_SERVICE_URL + "/delete/" + mealId);
    }

    @Override
    public List<MealDTO> getAllMeals() {
        ResponseEntity<MealDTO[]> response = restTemplate.getForEntity(MEAL_SERVICE_URL + "/all", MealDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<MealDTO> getMealsByPartnerId(Long partnerId) {
        // Use RestTemplate to call Meal Service and get meals by partnerId
        ResponseEntity<MealDTO[]> response = restTemplate.getForEntity(MEAL_SERVICE_URL + "/partner/" + partnerId, MealDTO[].class);

        // Convert the response to List<MealDTO>
        return Arrays.asList(response.getBody());
    }

    private PartnerDTO convertToDTO(Partner partner) {
        PartnerDTO dto = new PartnerDTO();
        dto.setPartnerId(partner.getPartnerId());
        dto.setPartnerName(partner.getPartnerName());
        dto.setShopName(partner.getShopName());
        dto.setPartnerEmail(partner.getPartnerEmail());
        dto.setPhoneNumber(partner.getPhoneNumber());
        dto.setRole(partner.getRole());
        dto.setAddress(partner.getAddress());
        dto.setCity(partner.getCity());
        dto.setState(partner.getState());
        dto.setZipCode(partner.getZipCode());
        dto.setShopDescription(partner.getShopDescription());
        dto.setLogoUrl(partner.getLogoUrl());
        dto.setMealIds(partner.getMealIds());
        return dto;
    }
}
