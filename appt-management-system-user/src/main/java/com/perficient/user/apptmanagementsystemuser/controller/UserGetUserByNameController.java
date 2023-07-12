package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserGetUserByNameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/")
public class UserGetUserByNameController {
    private final UserGetUserByNameService userGetUserByNameService;

    public UserGetUserByNameController(UserGetUserByNameService userGetUserByNameService) {
        this.userGetUserByNameService = userGetUserByNameService;
    }

    @GetMapping("/user/search/{firstName}/{lastName}")
    public ResponseEntity<?> searchUserByName(@PathVariable String firstName, @PathVariable String lastName){
        try{
            List<User> userList = userGetUserByNameService.searchUserByName(firstName, lastName);
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existing user found");
        }

    }

}