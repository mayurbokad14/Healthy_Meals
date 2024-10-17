package com.wipro.hma.cs.service;

import com.wipro.hma.cs.dto.CartDTO;

public interface CartService {

    CartDTO getCartDetails(Long userId);

    CartDTO addMealToCart(Long userId, Long mealId);

    void removeMealFromCart(Long userId, Long mealId);

    CartDTO getCartByUserId(Long userId);

    void clearCart(Long userId);


}
