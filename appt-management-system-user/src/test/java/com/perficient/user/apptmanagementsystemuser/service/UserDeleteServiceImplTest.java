package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDeleteServiceImplTest {
    private UserDeleteServiceImpl userDeleteService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userDeleteService = new UserDeleteServiceImpl(userRepository);
    }
    @Test
    void DeleteUser_ExistingUser_ShouldReturnTrue(){
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserEntity()));
        doNothing().when(userRepository).deleteById(userId);

        boolean result = userDeleteService.deleteUser(userId);

        assertTrue(result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
    @Test
    void deleteUser_NonExistingUser_ShouldReturnFalse() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        boolean result = userDeleteService.deleteUser(userId);

        assertFalse(result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).deleteById(userId);
    }

}