package com.wipro.hma.us.controller;

import com.wipro.hma.us.dto.CartDTO;
import com.wipro.hma.us.dto.MealDTO;
import com.wipro.hma.us.dto.UserDTO;
import com.wipro.hma.us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(value="pageNumber", defaultValue="0", required =false)Integer pageNumber,
                                                     @RequestParam(value="pageSize", defaultValue="10", required =false)Integer pageSize,
                                                     @RequestParam(value="sortBy", defaultValue="rollNo", required =false)String sortBy,
                                                     @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir) {
        List<UserDTO> userDTOs = userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/meals/all")
    public ResponseEntity<List<MealDTO>> getAllMeals() {
        List<MealDTO> meals = userService.getAllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @PostMapping("/{userId}/add/{mealId}")
    public ResponseEntity<CartDTO> addMealToCart(@PathVariable Long userId, @PathVariable Long mealId){
        CartDTO cartDTO = userService.addMealToCart(userId, mealId);
        return new ResponseEntity<>(cartDTO,  HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/remove/{mealId}")
    public ResponseEntity<Void> removeMealFromCart(@PathVariable Long userId, @PathVariable Long mealId) {
        userService.removeMealFromCart(userId, mealId);
        return ResponseEntity.noContent().build();
    }



}
