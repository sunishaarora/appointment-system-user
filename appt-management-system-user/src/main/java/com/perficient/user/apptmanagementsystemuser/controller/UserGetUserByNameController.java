package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserGetUserByNameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UserGetUserByNameController {
    private final UserGetUserByNameService userGetUserByNameService;

    public UserGetUserByNameController(UserGetUserByNameService userGetUserByNameService) {
        this.userGetUserByNameService = userGetUserByNameService;
    }

    @GetMapping("/user/search/{firstName}/{lastName}")
    public ResponseEntity<List<User>> searchUserByName(@PathVariable String firstName, @PathVariable String lastName){
        List<User> userList = userGetUserByNameService.searchUserByName(firstName, lastName);
        if(userList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}