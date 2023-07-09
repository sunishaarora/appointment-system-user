package com.perficient.user.apptmanagementsystemuser.repository;

import com.perficient.user.apptmanagementsystemuser.entity.UserEntity;
import com.perficient.user.apptmanagementsystemuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmailAddresses(String emailAddresses);

    List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
