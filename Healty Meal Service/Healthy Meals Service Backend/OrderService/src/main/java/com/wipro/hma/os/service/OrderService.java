package com.wipro.hma.os.service;

import com.wipro.hma.os.dto.CartDTO;
import com.wipro.hma.os.dto.OrderDTO;
import com.wipro.hma.os.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getAllOrders();
    void deleteOrder(Long orderId);
    List<Order> createOrdersFromCart(Long cart);
}
