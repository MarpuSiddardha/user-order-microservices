package com.siddardha.UserService.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @JsonProperty(index = 1)
    private Long id;
    
    @JsonProperty(index = 2)
    private String name;
    
    @JsonProperty(index = 3)
    private String email;
}
