package com.wipro.hma.os.service;

import com.wipro.hma.os.dto.CartDTO;
import com.wipro.hma.os.dto.MealDTO;
import com.wipro.hma.os.dto.OrderDTO;
import com.wipro.hma.os.entity.Order;
import com.wipro.hma.os.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    RestTemplate restTemplate;

    private final String MEAL_SERVICE_URL = "http://localhost:8082/api/meals";
    private final String CART_SERVICE_URL = "http://localhost:8086/api/cart";


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setMealIds(orderDTO.getMealIds());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCreatedAt(LocalDateTime.now());

        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }


    // Method to create orders based on cart
    @Override
    public List<Order> createOrdersFromCart(Long cartId) {

        CartDTO cart = restTemplate.getForObject(CART_SERVICE_URL+"/"+cartId, CartDTO.class);
        System.out.println(cart.toString());

        // Fetch meal details from Meal Service using RestTemplate
        List<Long> mealIds = cart.getMealIds(); // Get mealIds from the cart
        Map<Long, List<MealDTO>> mealsByPartner = new HashMap<>();

        for (Long mealId : mealIds) {
            // Call the Meal Service for each mealId
            MealDTO meal = restTemplate.getForObject(MEAL_SERVICE_URL + "/" + mealId, MealDTO.class);

            if (meal == null) {
                throw new RuntimeException("Meal with ID " + mealId + " not found.");
            }

            // Group meals by partnerId
            mealsByPartner.computeIfAbsent(meal.getPartnerId(), k -> new ArrayList<>()).add(meal);
        }

        // Create orders for each partner
        List<Order> orders = new ArrayList<>();
        for (Map.Entry<Long, List<MealDTO>> entry : mealsByPartner.entrySet()) {
            Long partnerId = entry.getKey();
            List<MealDTO> partnerMeals = entry.getValue();

            // Calculate total price for this partner's meals
            double totalPrice = partnerMeals.stream()
                    .mapToDouble(MealDTO::getPrice)
                    .sum();

            // Create an order for the partner
            Order order = new Order();
            order.setUserId(cart.getUserId());
            order.setPartnerId(partnerId);  // Set the partner ID
            order.setMealIds(partnerMeals.stream().map(MealDTO::getMealId).collect(Collectors.toList())); // Add meal IDs to order
            order.setTotalPrice(totalPrice);
            order.setCreatedAt(LocalDateTime.now());

            orders.add(orderRepository.save(order));
        }

        return orders;
    }


    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setUserId(order.getUserId());
        dto.setMealIds(order.getMealIds());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setCreatedAt(order.getCreatedAt());
        return dto;
    }
}
