package com.siddardha.UserService.Service;

import com.siddardha.UserService.DTO.UserRequestDTO;
import com.siddardha.UserService.DTO.UserResponseDTO;
import com.siddardha.UserService.Entity.User;
import com.siddardha.UserService.Exception.UserAlreadyExistsException;
import com.siddardha.UserService.Exception.UserNotFoundException;
import com.siddardha.UserService.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public User createUser(UserRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        User user = new User();
        user.setId(request.getId());
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
