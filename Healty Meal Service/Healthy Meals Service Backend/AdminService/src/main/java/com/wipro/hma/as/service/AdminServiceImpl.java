package com.wipro.hma.as.service;

import com.wipro.hma.as.dto.MealDTO;
import com.wipro.hma.as.dto.OrderDTO;
import com.wipro.hma.as.dto.PartnerDTO;
import com.wipro.hma.as.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private RestTemplate restTemplate;

    // URLs for other microservices
    private final String USER_SERVICE_URL = "http://localhost:8089/private/users";
    private final String MEAL_SERVICE_URL = "http://localhost:8082/private/meals";
    private final String ORDER_SERVICE_URL = "http://localhost:8083/private/orders";
    private final String PARTNER_SERVICE_URL = "http://localhost:8084/private/partners";

    // ---------------------------------- User Operations ----------------------------------

    @Override
    public List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        // Build the URL with query parameters for pagination and sorting
        String url = USER_SERVICE_URL + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize + "&sortBy=" + sortBy + "&sortDir=" + sortDir;

        // Fetch the user data from the external service
        UserDTO[] users = restTemplate.getForObject(url, UserDTO[].class);

        // Convert the array to a List and return it
        return Arrays.asList(users);
    }


    @Override
    public UserDTO getUserById(Long userId) {
        return restTemplate.getForObject(USER_SERVICE_URL + "/" + userId, UserDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        restTemplate.delete(USER_SERVICE_URL + "/" + userId);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        restTemplate.put(USER_SERVICE_URL + "/" + userId, userDTO);
        return userDTO;
    }

    // ---------------------------------- Partner Operations ----------------------------------

    @Override
    public List<PartnerDTO> getAllPartners() {
        PartnerDTO[] partners = restTemplate.getForObject(PARTNER_SERVICE_URL, PartnerDTO[].class);
        return Arrays.asList(partners);
    }

    @Override
    public PartnerDTO getPartnerById(Long partnerId) {
        return restTemplate.getForObject(PARTNER_SERVICE_URL + "/" + partnerId, PartnerDTO.class);
    }

    @Override
    public void deletePartner(Long partnerId) {
        restTemplate.delete(PARTNER_SERVICE_URL + "/" + partnerId);
    }

    @Override
    public PartnerDTO updatePartner(Long partnerId, PartnerDTO partnerDTO) {
        restTemplate.put(PARTNER_SERVICE_URL + "/" + partnerId, partnerDTO);
        return partnerDTO;
    }

    // ---------------------------------- Meal Operations ----------------------------------

    @Override
    public List<MealDTO> getAllMeals() {
        MealDTO[] meals = restTemplate.getForObject(MEAL_SERVICE_URL, MealDTO[].class);
        return Arrays.asList(meals);
    }

    @Override
    public MealDTO getMealById(Long mealId) {
        return restTemplate.getForObject(MEAL_SERVICE_URL + "/" + mealId, MealDTO.class);
    }

    @Override
    public void deleteMeal(Long mealId) {
        restTemplate.delete(MEAL_SERVICE_URL + "/" + mealId);
    }

    @Override
    public MealDTO updateMeal(Long mealId, MealDTO mealDTO) {
        restTemplate.put(MEAL_SERVICE_URL + "/" + mealId, mealDTO);
        return mealDTO;
    }

    // ---------------------------------- Order Operations ----------------------------------

    @Override
    public List<OrderDTO> getAllOrders() {
        OrderDTO[] orders = restTemplate.getForObject(ORDER_SERVICE_URL, OrderDTO[].class);
        return Arrays.asList(orders);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return restTemplate.getForObject(ORDER_SERVICE_URL + "/" + orderId, OrderDTO.class);
    }

    @Override
    public void deleteOrder(Long orderId) {
        restTemplate.delete(ORDER_SERVICE_URL + "/" + orderId);
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        restTemplate.put(ORDER_SERVICE_URL + "/" + orderId, orderDTO);
        return orderDTO;
    }
}
