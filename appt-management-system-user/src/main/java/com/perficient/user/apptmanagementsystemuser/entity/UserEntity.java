package com.perficient.user.apptmanagementsystemuser.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="user_details")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emailAddresses;
    private String phoneNumbers;
}
