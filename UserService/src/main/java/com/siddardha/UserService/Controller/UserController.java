package com.siddardha.UserService.Controller;

import com.siddardha.UserService.DTO.UserRequestDTO;
import com.siddardha.UserService.DTO.UserResponseDTO;
import com.siddardha.UserService.Entity.User;
import com.siddardha.UserService.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService ) {
        this.userService  = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO request) {
        User createdUser =  userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id) {
        User user  = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        List<User> users  = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
