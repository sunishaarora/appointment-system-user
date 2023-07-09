package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGetUserByNameServiceImpl implements UserGetUserByNameService{
    private UserRepository userRepository;

    public UserGetUserByNameServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> searchUserByName(String firstName, String lastName) {
        List<UserEntity> userEntities = userRepository.findByFirstNameAndLastName(firstName, lastName);
        List<User> users =userEntities
                .stream()
                .map(userEntity -> new User(
                        userEntity.getUserId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getGender(),
                        userEntity.getAge(),
                        userEntity.getEmailAddresses(),
                        userEntity.getPhoneNumbers()))
                .collect(Collectors.toList());
        return users;
    }
}
