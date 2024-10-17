package com.wipro.hma.os.controller;

import com.wipro.hma.os.dto.OrderDTO;
import com.wipro.hma.os.entity.Order;
import com.wipro.hma.os.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create/{cartId}")
    public ResponseEntity<List<Order>> createOrders(@PathVariable("cartId") Long cartId) {
        try {
            // Call the service method to create orders from cart
            List<Order> orders = orderService.createOrdersFromCart(cartId);

            // Return the created orders with HTTP status 201 (Created)
            return new ResponseEntity<>(orders, HttpStatus.CREATED);

        } catch (Exception e) {
            // Handle any exceptions and return a bad request response
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
