package com.wipro.hma.as.service;


import com.wipro.hma.as.dto.MealDTO;
import com.wipro.hma.as.dto.OrderDTO;
import com.wipro.hma.as.dto.PartnerDTO;
import com.wipro.hma.as.dto.UserDTO;

import java.util.List;

public interface AdminService {

    List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    UserDTO getUserById(Long userId);

    void deleteUser(Long userId);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    // ---------------------------------- Partner Operations ----------------------------------

    List<PartnerDTO> getAllPartners();

    PartnerDTO getPartnerById(Long partnerId);

    void deletePartner(Long partnerId);

    PartnerDTO updatePartner(Long partnerId, PartnerDTO partnerDTO);

    // ---------------------------------- Meal Operations ----------------------------------

    List<MealDTO> getAllMeals();

    MealDTO getMealById(Long mealId);

    void deleteMeal(Long mealId);

    MealDTO updateMeal(Long mealId, MealDTO mealDTO);

    // ---------------------------------- Order Operations ----------------------------------

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(Long orderId);

    void deleteOrder(Long orderId);

    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);

}
