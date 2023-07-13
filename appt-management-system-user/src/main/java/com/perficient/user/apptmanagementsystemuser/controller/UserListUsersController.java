package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserListUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UserListUsersController {
    private final UserListUsersService userListUsersService;
    public UserListUsersController(UserListUsersService userListUsersService) {
        this.userListUsersService = userListUsersService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try{
            List<User> userList = userListUsersService.getAllUsers();
            return ResponseEntity.ok(userList);
        }catch (EmptyListException ex){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existing users");
        }

    }
}
