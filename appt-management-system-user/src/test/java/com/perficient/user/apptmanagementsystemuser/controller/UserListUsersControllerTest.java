package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserListUsersService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserListUsersControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserListUsersService userListUsersService;
    @InjectMocks
    private UserListUsersController userListUsersController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userListUsersController).build();
    }

    @Test
    void ListUsers_Success(){
        List<User> userList = new ArrayList<>();
        when(userListUsersService.getAllUsers()).thenReturn(userList);
        ResponseEntity<?> response = userListUsersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void listUsers_EmptyListException(){
        String errorMessage = "No existing users";
        when(userListUsersService.getAllUsers()).thenThrow(new EmptyListException(errorMessage));

        ResponseEntity<?> response = userListUsersController.getAllUsers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

}