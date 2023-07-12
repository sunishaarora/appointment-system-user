package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.controller.DuplicateEmailException;
import com.perficient.user.apptmanagementsystemuser.controller.UserCreateController;
import com.perficient.user.apptmanagementsystemuser.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserCreateServiceTest {
    private UserCreateController userCreateController;
    @Mock
    private UserCreateService userCreateService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userCreateController = new UserCreateController(userCreateService);
    }

    @Test
    void createUser_ValidUser_ReturnsOkResponse(){
        User user = new User();
        when(userCreateService.createUser(user)).thenReturn(user);
        ResponseEntity<?> response = userCreateController.createUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void handleDuplicateEmailException_ValidException_ReturnsConflictResponse() {
        DuplicateEmailException exception = new DuplicateEmailException("Email address already exists");
        String expectedMessage = "Email address already exists";

        ResponseEntity<String> response = userCreateController.handleDuplicateEmailException(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody());
    }
}