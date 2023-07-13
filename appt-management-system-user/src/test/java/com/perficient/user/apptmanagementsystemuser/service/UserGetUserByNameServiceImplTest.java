package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserGetUserByNameServiceImplTest {
    private UserGetUserByNameServiceImpl userGetUserByNameService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userGetUserByNameService = new UserGetUserByNameServiceImpl(userRepository);
    }
    @Test
    void searchUserByName_ExistingUsers_ShouldReturnMatchingUsers() {
        String firstName = "John";
        String lastName = "Doe";

        UserEntity userEntity1 = createUserEntity(1L, "John", "Doe", "Male", 30, "john.doe@example.com", "1234567890");
        UserEntity userEntity2 = createUserEntity(2L, "John", "Smith", "Male", 35, "john.smith@example.com", "9876543210");

        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(userEntity1);
        userEntities.add(userEntity2);

        when(userRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(userEntities);

        List<User> result = userGetUserByNameService.searchUserByName(firstName, lastName);

        assertEquals(2, result.size());
        assertEquals(firstName, result.get(0).getFirstName());
        assertEquals(lastName, result.get(0).getLastName());
        assertEquals(firstName, result.get(1).getFirstName());
        assertEquals("Smith", result.get(1).getLastName());
    }

    @Test
    void searchUserByName_NonExistingUsers_ShouldReturnEmptyList() {
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(new ArrayList<>());

        assertThrows(NoSuchElementException.class, () ->{
            userGetUserByNameService.searchUserByName(firstName, lastName);
        });
    }

    @Test
    private UserEntity createUserEntity(Long userId, String firstName, String lastName, String gender, int age,
                                        String emailAddresses, String phoneNumbers) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setAge(age);
        userEntity.setEmailAddresses(emailAddresses);
        userEntity.setPhoneNumbers(phoneNumbers);
        return userEntity;
    }
}