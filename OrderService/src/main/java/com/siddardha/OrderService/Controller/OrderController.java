package com.siddardha.OrderService.Controller;
import com.siddardha.OrderService.DTO.OrderRequestDTO;
import com.siddardha.OrderService.Entity.Order;
import com.siddardha.OrderService.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService  = orderService;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequestDTO request) {
        Order placedOrder  = orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(placedOrder);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserOrders(@PathVariable Long userId) {
       List<Order> userOrders = orderService.getOrderByUser(userId);
       return ResponseEntity.status(HttpStatus.FOUND).body(userOrders);
    }
}
