package com.wipro.hma.cs.controller;

import com.wipro.hma.cs.dto.CartDTO;
import com.wipro.hma.cs.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}/details")
    public ResponseEntity<CartDTO> getCartDetails(@PathVariable Long userId) {
        // Fetch and display cart details for the specified userId
        CartDTO cart = cartService.getCartDetails(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/add/{mealId}")
    public ResponseEntity<CartDTO> addMealToCart(@PathVariable Long userId, @PathVariable Long mealId) {
        CartDTO cart = cartService.addMealToCart(userId, mealId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{userId}/remove/{mealId}")
    public ResponseEntity<Void> removeMealFromCart(@PathVariable Long userId, @PathVariable Long mealId) {
        cartService.removeMealFromCart(userId, mealId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
