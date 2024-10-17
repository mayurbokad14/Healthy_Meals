package com.wipro.hma.us.service;

import com.wipro.hma.us.dto.CartDTO;
import com.wipro.hma.us.dto.MealDTO;
import com.wipro.hma.us.dto.UserDTO;
import com.wipro.hma.us.exception.UsernameNotFoundException;
import com.wipro.hma.us.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDTO updateUser(Long userId, UserDTO userDTO);

    UserDTO getUserById(Long userId);

    void deleteUser(Long userId);

    List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);


    List<MealDTO> getAllMeals();

    UserDTO getUserByUsername(String username);

    CartDTO addMealToCart(Long userId, Long mealId);

    void removeMealFromCart(Long userId, Long mealId);

}
