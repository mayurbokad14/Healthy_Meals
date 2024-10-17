package com.wipro.hma.us.service;

import com.wipro.hma.us.dto.CartDTO;
import com.wipro.hma.us.dto.MealDTO;
import com.wipro.hma.us.dto.UserDTO;
import com.wipro.hma.us.entity.User;
import com.wipro.hma.us.exception.UsernameNotFoundException;
import com.wipro.hma.us.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    // Meal Service URL
    private static final String MEAL_SERVICE_URL = "http://localhost:8082/api/meals";
    private static final String CART_SERVICE_URL = "http://localhost:8086/api/cart";

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setUserEmail(userDTO.getUserEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
//        user.setPassword(userDTO.getPassword()); // Encrypt password4
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setZipCode(userDTO.getZipCode());

        user = userRepository.save(user);
        return convertToDTO(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {

        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setUserEmail(userDTO.getUserEmail());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setCity(userDTO.getCity());
        existingUser.setState(userDTO.getState());
        existingUser.setZipCode(userDTO.getZipCode());

        userRepository.save(existingUser);
        return convertToDTO(existingUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> users = userRepository.findAll(p);
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MealDTO> getAllMeals() {

        ResponseEntity<MealDTO[]> response = restTemplate.getForEntity(MEAL_SERVICE_URL + "/all", MealDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return convertToDTO(user);
    }

    @Override
    public CartDTO addMealToCart(Long userId,  Long mealId) {
        return restTemplate.postForObject(CART_SERVICE_URL +"/"+ userId + "/add/"+ mealId, null, CartDTO.class);
    }


    @Override
    public void removeMealFromCart(Long userId, Long mealId) {
        restTemplate.delete(CART_SERVICE_URL + "/" + userId + "/remove/" + mealId);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setUserEmail(user.getUserEmail());
        dto.setPassword(user.getPassword());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setState(user.getState());
        dto.setZipCode(user.getZipCode());
        dto.setRole(String.valueOf(user.getRole()));
        return dto;
    }

}
