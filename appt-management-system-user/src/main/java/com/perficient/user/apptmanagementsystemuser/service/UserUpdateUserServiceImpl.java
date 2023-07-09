package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserUpdateUserServiceImpl implements UserUpdateUserService{
    private UserRepository userRepository;

    public UserUpdateUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUser(Long userId, User user) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setAge(user.getAge());
            userEntity.setGender(user.getGender());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setEmailAddresses(user.getEmailAddresses());
            userEntity.setPhoneNumbers(user.getPhoneNumbers());
            userRepository.save(userEntity);
            return user;
        } else {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
    }

}

