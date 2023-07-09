package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserUpdateUserServiceImplTest {
    private UserUpdateUserServiceImpl userUpdateUserService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUpdateUserService = new UserUpdateUserServiceImpl(userRepository);
    }
    @Test
    void updateUser_ExistingUser_ShouldReturnUpdatedUser() {
        Long userId = 1L;
        User userToUpdate = createUser(userId, "John", "Doe", "Male", 30, "john.doe@example.com", "1234567890");

        UserEntity existingUserEntity = createUserEntity(userId, "John", "Doe", "Male", 30, "john.doe@example.com", "1234567890");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserEntity));
        when(userRepository.save(existingUserEntity)).thenReturn(existingUserEntity);

        User updatedUser = userUpdateUserService.updateUser(userId, userToUpdate);

        assertEquals(userToUpdate, updatedUser);
        assertEquals(userToUpdate.getFirstName(), existingUserEntity.getFirstName());
        assertEquals(userToUpdate.getLastName(), existingUserEntity.getLastName());
        assertEquals(userToUpdate.getGender(), existingUserEntity.getGender());
        assertEquals(userToUpdate.getAge(), existingUserEntity.getAge());
        assertEquals(userToUpdate.getEmailAddresses(), existingUserEntity.getEmailAddresses());
        assertEquals(userToUpdate.getPhoneNumbers(), existingUserEntity.getPhoneNumbers());
    }

    @Test
    void updateUser_NonExistingUser_ShouldReturnNull() {
        Long userId = 1L;
        User userToUpdate = createUser(userId, "John", "Doe", "Male", 30, "john.doe@example.com", "1234567890");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userUpdateUserService.updateUser(userId, userToUpdate));
    }


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

    private User createUser(Long userId, String firstName, String lastName, String gender, int age,
                            String emailAddresses, String phoneNumbers) {
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setAge(age);
        user.setEmailAddresses(emailAddresses);
        user.setPhoneNumbers(phoneNumbers);
        return user;
    }

}