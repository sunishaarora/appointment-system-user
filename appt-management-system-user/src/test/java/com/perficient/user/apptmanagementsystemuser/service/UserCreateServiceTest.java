package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.controller.DuplicateEmailException;
import com.perficient.user.apptmanagementsystemuser.controller.UserCreateController;
import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserCreateServiceTest {
    //private UserCreateController userCreateController;
    //@Mock
    @InjectMocks
    private UserCreateServiceImpl userCreateService;
    @Mock
    private UserRepository userRepository;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        userCreateController = new UserCreateController(userCreateService);
//    }

    @Test
    void createUser_ValidUser_ReturnsOkResponse() {
        User user = User.builder().age(25).gender("Male").firstName("John").lastName("Doe").build();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        user.setEmailAddresses("aaa@mst.edu");

        when(userRepository.existsByEmailAddresses(any())).thenReturn(Boolean.FALSE);
        when(userRepository.save(any())).thenReturn(userEntity);

        User newUser = userCreateService.createUser(user);

        verify(userRepository).save(any());
        assertNotNull(newUser);
        assertEquals(1L, newUser.getUserId());
    }

    @Test
    void handleDuplicateEmailException_ValidException_ReturnsConflictResponse() {
//        String duplicateEmail = "test@example.com";
//        User user = new User();
//        user.setEmailAddresses(duplicateEmail);
//        when(userCreateService.createUser(user)).thenThrow(DuplicateEmailException.class);
//        ResponseEntity<?> response = userCreateController.createUser(user);
//        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
//        assertEquals("Email ID already exists, could not create user", response.getBody());
    }

}