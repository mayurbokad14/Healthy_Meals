package com.wipro.hma.cs.service;

import com.wipro.hma.cs.dto.CartDTO;
import com.wipro.hma.cs.dto.MealDTO;
import com.wipro.hma.cs.entity.Cart;
import com.wipro.hma.cs.exception.CartNotFoundException;
import com.wipro.hma.cs.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

//    @Autowired
//    private MealService mealService; // Assuming MealService is available to get meal info

    @Autowired
    private RestTemplate restTemplate;

    private static final String MEAL_SERVICE_URL = "http://localhost:8082/api/meals/";

    @Override
    public CartDTO getCartDetails(Long userId) {
        // Fetch the cart by userId
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

        // Convert the cart entity to a DTO and return it
        return convertToDTO(cart);
    }

    // Method to add a meal for the user's cart
    @Override
    public CartDTO addMealToCart(Long userId, Long mealId) {
        System.out.println("Userid: "+userId);
        // Fetch meal details from the Meal Service
        MealDTO meal = restTemplate.getForObject(MEAL_SERVICE_URL + mealId, MealDTO.class);

        // Find the cart by userId, or create a new one if it doesn't exist
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());


        // Set the userId in the cart if it's a new cart
        if (cart.getCartId() == null) {
            cart.setUserId(userId);
        }
        cart.setTotalPrice(cart.getTotalPrice().add(BigDecimal.valueOf(meal.getPrice())));

        cart.getMealIds().add(mealId);

        // Save the updated cart and return it
        cartRepository.save(cart);
        return convertToDTO(cart);
    }


    @Override
    public void removeMealFromCart(Long userId, Long mealId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user: " + userId));

        MealDTO meal = restTemplate.getForObject(MEAL_SERVICE_URL + mealId, MealDTO.class);

        cart.setTotalPrice(cart.getTotalPrice().subtract(BigDecimal.valueOf(meal.getPrice())));
        cart.getMealIds().remove(mealId);
        cartRepository.save(cart);
    }

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for userId: " + userId));
        // Initialize a list to hold meal details
        List<MealDTO> mealDetailsList = new ArrayList<>();

        // Fetch details of each meal from the Meal Service
        for (Long mealId : cart.getMealIds()) {
            MealDTO meal = restTemplate.getForObject(MEAL_SERVICE_URL + "/" + mealId, MealDTO.class);
            if (meal != null) {
                mealDetailsList.add(meal); // Add meal details to the list
            }
        }

        // Convert Cart entity to CartDTO
        CartDTO cartDTO = convertToDTO(cart);

        // Set the fetched meal details in the CartDTO
        cartDTO.setMeals(mealDetailsList);

        return cartDTO;
    }

    @Override
    public void clearCart(Long userId) {            //http://localhost:8086/api/cart/clear?userId=1
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        cart.getMealIds().clear();
        cart.setTotalPrice(BigDecimal.valueOf(0.0));

        cartRepository.save(cart);
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setMealIds(cart.getMealIds());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        return cartDTO;
    }
}
