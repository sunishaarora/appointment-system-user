package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.controller.EmptyListException;
import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserListUsersServiceImpl implements UserListUsersService{
    @Autowired
    private UserRepository userRepository;
    public UserListUsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        if (userEntities.isEmpty()) {
            throw new EmptyListException("User list is empty");
        }
        List<User> users = userEntities
                .stream()
                .map(userEntity -> new User(
                        userEntity.getUserId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getGender(),
                        userEntity.getAge(),
                        userEntity.getEmailAddresses(),
                        userEntity.getPhoneNumbers()
                ))
                .collect(Collectors.toList());
        return users;
    }
}
