package com.siddardha.OrderService.DTO;

import lombok.Data;

@Data
public class OrderRequestDTO {

    private Long id;

    private Long userId;

    private String productName;

    private Double amount;
}
