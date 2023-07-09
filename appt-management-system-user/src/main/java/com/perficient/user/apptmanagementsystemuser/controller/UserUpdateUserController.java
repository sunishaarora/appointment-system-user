package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserUpdateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class UserUpdateUserController {
    private UserUpdateUserService userUpdateUserService;

    public UserUpdateUserController(UserUpdateUserService userUpdateUserService) {
        this.userUpdateUserService = userUpdateUserService;
    }
    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){
        user = userUpdateUserService.updateUser(userId, user);
        return ResponseEntity.ok(user);
    }
}
