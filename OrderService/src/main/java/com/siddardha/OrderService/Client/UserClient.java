package com.siddardha.OrderService.Client;

import com.siddardha.OrderService.DTO.UserSummaryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "${user-service.base-url}" // http:localhost:8080
 )
public interface UserClient {

    @GetMapping("/api/users/{id}")
    UserSummaryDTO getUserById(@PathVariable("id") Long id);

}
