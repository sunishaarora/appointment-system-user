package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserCreateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins= "http://localhost:3000")
public class UserCreateController {
    private final UserCreateService userCreateService;
    public UserCreateController(UserCreateService userCreateService) {
        this.userCreateService = userCreateService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            User savedUser = userCreateService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch(DuplicateEmailException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ID already exists, could not create user");
        }

    }

}