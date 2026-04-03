package com.siddardha.OrderService.Service;

import com.siddardha.OrderService.Client.UserClient;
import com.siddardha.OrderService.DTO.OrderRequestDTO;
import com.siddardha.OrderService.DTO.UserSummaryDTO;
import com.siddardha.OrderService.Entity.Order;
import com.siddardha.OrderService.Exception.BadRequestException;
import com.siddardha.OrderService.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserClient userClient;

    public OrderService(OrderRepository orderRepository, UserClient userClient){
        this.orderRepository = orderRepository;
        this.userClient = userClient;
    }

    public Order placeOrder(OrderRequestDTO request) {
        if(request.getUserId()==null) {
            throw new BadRequestException("User id is required");
        }
        UserSummaryDTO user  = userClient.getUserById(request.getUserId());
        Order order  = new Order();
        order.setId(request.getId());
        order.setUserId(request.getUserId());
        order.setProductName(request.getProductName());
        order.setAmount(request.getAmount());

        return orderRepository.save(order);
    }

    public List<Order> getOrderByUser(Long userId){
        return orderRepository.findAll()
                .stream()
                .filter(o-> o.getUserId().equals(userId))
                .toList();
    }
}
