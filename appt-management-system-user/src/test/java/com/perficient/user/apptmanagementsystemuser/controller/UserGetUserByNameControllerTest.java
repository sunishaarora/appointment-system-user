package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserGetUserByNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserGetUserByNameControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private UserGetUserByNameController userGetUserByNameController;
    @Mock
    private UserGetUserByNameService userGetUserByNameService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userGetUserByNameController).build();
    }
    @Test
    void getUserByName_Success() {
        String firstName = "John";
        String lastName = "Doe";
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName(firstName);
        user1.setLastName(lastName);
        userList.add(user1);
        when(userGetUserByNameService.searchUserByName(firstName, lastName)).thenReturn(userList);
        ResponseEntity<?> response = userGetUserByNameController.searchUserByName(firstName, lastName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }
    @Test
    void getUserByName_UserNotFound(){
        String firstName = "John";
        String lastName = "Doe";
        when(userGetUserByNameService.searchUserByName(firstName, lastName)).thenReturn(new ArrayList<>());
        ResponseEntity<?> response = userGetUserByNameController.searchUserByName(firstName, lastName);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}