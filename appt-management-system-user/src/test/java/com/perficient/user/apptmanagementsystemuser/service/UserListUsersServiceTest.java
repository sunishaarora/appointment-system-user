package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.controller.EmptyListException;
import com.perficient.user.apptmanagementsystemuser.controller.UserListUsersController;
import com.perficient.user.apptmanagementsystemuser.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserListUsersServiceTest {
    private UserListUsersController userListUsersController;
    @Mock
    private UserListUsersService userListUsersService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userListUsersController = new UserListUsersController(userListUsersService);
    }

    @Test
    void getAllUsers_ValidUsers_ReturnsOkResponseWithUserList() {
        User user1 = new User(1L, "John", "Doe", "Male", 30, "john@example.com", "123456789");
        User user2 = new User(2L, "Jane", "Smith", "Female", 25, "jane@example.com", "987654321");
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userListUsersService.getAllUsers()).thenReturn(expectedUsers);

        ResponseEntity<?> response = userListUsersController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());
    }

    @Test
    void handleEmptyListException_ValidException_ReturnsNoContentResponse() {
        when(userListUsersService.getAllUsers()).thenThrow(new EmptyListException("No existing users"));
        ResponseEntity<?> response = userListUsersController.getAllUsers();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("No existing users", response.getBody());
    }
}