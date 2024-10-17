package com.wipro.hma.as.controller;

import com.wipro.hma.as.dto.MealDTO;
import com.wipro.hma.as.dto.OrderDTO;
import com.wipro.hma.as.dto.PartnerDTO;
import com.wipro.hma.as.dto.UserDTO;
import com.wipro.hma.as.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ---------------------------------- User Endpoints ----------------------------------

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = "username") String sortBy,
                                                     @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        // Pass the parameters to the service
        List<UserDTO> users = adminService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);

        // Return the list of users in the response
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getUserById(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(adminService.updateUser(userId, userDTO));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------------- Partner Endpoints ----------------------------------

    @GetMapping("/partners")
    public ResponseEntity<List<PartnerDTO>> getAllPartners() {
        return ResponseEntity.ok(adminService.getAllPartners());
    }

    @GetMapping("/partners/{partnerId}")
    public ResponseEntity<PartnerDTO> getPartnerById(@PathVariable Long partnerId) {
        return ResponseEntity.ok(adminService.getPartnerById(partnerId));
    }

    @PutMapping("/partners/{partnerId}")
    public ResponseEntity<PartnerDTO> updatePartner(@PathVariable Long partnerId, @RequestBody PartnerDTO partnerDTO) {
        return ResponseEntity.ok(adminService.updatePartner(partnerId, partnerDTO));
    }

    @DeleteMapping("/partners/{partnerId}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long partnerId) {
        adminService.deletePartner(partnerId);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------------- Meal Endpoints ----------------------------------

    @GetMapping("/meals")
    public ResponseEntity<List<MealDTO>> getAllMeals() {
        return ResponseEntity.ok(adminService.getAllMeals());
    }

    @GetMapping("/meals/{mealId}")
    public ResponseEntity<MealDTO> getMealById(@PathVariable Long mealId) {
        return ResponseEntity.ok(adminService.getMealById(mealId));
    }

    @PutMapping("/meals/{mealId}")
    public ResponseEntity<MealDTO> updateMeal(@PathVariable Long mealId, @RequestBody MealDTO mealDTO) {
        return ResponseEntity.ok(adminService.updateMeal(mealId, mealDTO));
    }

    @DeleteMapping("/meals/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long mealId) {
        adminService.deleteMeal(mealId);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------------- Order Endpoints ----------------------------------

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(adminService.getAllOrders());
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(adminService.getOrderById(orderId));
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(adminService.updateOrder(orderId, orderDTO));
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        adminService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
