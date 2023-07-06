package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import com.perficient.user.apptmanagementsystemuser.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateServiceImpl implements UserCreateService{
    private UserRepository userRepository;
    @Autowired
    public UserCreateServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        userEntity.setUserId(savedUserEntity.getUserId());
        return user;
    }


}
