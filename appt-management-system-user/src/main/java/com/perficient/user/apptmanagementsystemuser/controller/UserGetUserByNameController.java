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
@CrossOrigin(origins= "http://localhost:3000")
public class UserGetUserByNameController {
    private final UserGetUserByNameService userGetUserByNameService;

    public UserGetUserByNameController(UserGetUserByNameService userGetUserByNameService) {
        this.userGetUserByNameService = userGetUserByNameService;
    }

    //Feedback: Avoid verbs in URL, like don't need search here
    //Should be plural (users)
    //GET /users
    //GET /users/{id}
    //POST /users
    //PUT /users
    //PATCH /users --Update one field (what if change to null?-handles this case)
    //DELETE /users/{id}
    //Search should return empty list instead of 404
    //%FirstName% - Use query parameters for filter (Not path variable) - Like amazon
    //Search by different parameters
    //Other things to potentially add: Pagination/Sorting
    //GET /customers/{c_id}/orders/{o_id} (Good use of Path variables)
    //GET /orders?c_id=

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