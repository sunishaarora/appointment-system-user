package com.perficient.user.apptmanagementsystemuser.controller;

import com.perficient.user.apptmanagementsystemuser.controller.UserCreateController;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.service.UserCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserCreateControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserCreateService userCreateService;

    @InjectMocks
    private UserCreateController userCreateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userCreateController).build();
    }

    @Test
    void createUser_Success() throws Exception {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmailAddresses("john.doe@example.com");
        user.setGender("Male");
        user.setAge(25);
        user.setPhoneNumbers("123-456-7890");

        when(userCreateService.createUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailAddresses\":\"john.doe@example.com\",\"gender\":\"Male\",\"age\":25,\"phoneNumbers\":\"123-456-7890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.emailAddresses").value(user.getEmailAddresses()))
                .andExpect(jsonPath("$.gender").value(user.getGender()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andExpect(jsonPath("$.phoneNumbers").value(user.getPhoneNumbers()));

        verify(userCreateService).createUser(user);
    }

    @Test
    void createUser_DuplicateEmail() throws Exception {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmailAddresses("john.doe@example.com");
        user.setGender("Male");
        user.setAge(25);
        user.setPhoneNumbers("123-456-7890");

        when(userCreateService.createUser(user)).thenThrow(new DuplicateEmailException("Email ID already exists, could not create user"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailAddresses\":\"john.doe@example.com\",\"gender\":\"Male\",\"age\":25,\"phoneNumbers\":\"123-456-7890\"}"))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email ID already exists, could not create user"));

        verify(userCreateService).createUser(user);
    }
}