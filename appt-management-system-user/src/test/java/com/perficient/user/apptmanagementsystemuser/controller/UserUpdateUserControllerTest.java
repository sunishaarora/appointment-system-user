package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserUpdateUserService;
import com.perficient.user.apptmanagementsystemuser.service.UserUpdateUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
class UserUpdateUserControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private UserUpdateUserController userUpdateUserController;
    @Mock
    private UserUpdateUserService userUpdateUserService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userUpdateUserController).build();
    }
    @Test
    void updateUser_Success(){
        Long userId = 1L;
        User user = new User();

        when(userUpdateUserService.updateUser(userId, user)).thenReturn(user);
        ResponseEntity<User> response = userUpdateUserController.updateUser(userId, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

}