package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.service.UserDeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDeleteControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserDeleteService  userDeleteService;
    @InjectMocks
    private UserDeleteController userDeleteController;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userDeleteController).build();
    }

    @Test
    void deleteUser_Success(){
        Long userId = 1L;
        when(userDeleteService.deleteUser(userId)).thenReturn(true);
        ResponseEntity<?> response = userDeleteController.deleteUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteUser_NotFound(){
        Long userId = 1L;
        when(userDeleteService.deleteUser(userId)).thenReturn(false);
        ResponseEntity<?> response = userDeleteController.deleteUser(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}