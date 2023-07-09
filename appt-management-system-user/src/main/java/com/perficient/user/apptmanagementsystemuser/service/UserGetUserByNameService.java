package com.perficient.user.apptmanagementsystemuser.service;

import com.perficient.user.apptmanagementsystemuser.model.User;

import java.util.List;

public interface UserGetUserByNameService {
    List<User> searchUserByName(String firstName, String lastName);
}
