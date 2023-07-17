package com.perficient.user.apptmanagementsystemuser.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testConstructorAndGetters() {
        Long userId = 1L;
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String emailAddresses = "test@gmail.com";
        String phoneNumbers = "314xxxxxxx";
        String gender = "test gender";
        int age = 20;

        User user = new User(userId, firstName, lastName, gender, age, emailAddresses, phoneNumbers);
        Assertions.assertEquals(userId, user.getUserId());
        Assertions.assertEquals(userId, user.getUserId());
        Assertions.assertEquals(firstName, user.getFirstName());
        Assertions.assertEquals(lastName, user.getLastName());
        Assertions.assertEquals(gender, user.getGender());
        Assertions.assertEquals(age, user.getAge());
        Assertions.assertEquals(emailAddresses, user.getEmailAddresses());
        Assertions.assertEquals(phoneNumbers, user.getPhoneNumbers());
    }
    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User(1L, "Test firstName", "Test lastName", "test gender", 20, "test@gmail.com", "314xxxxxxx");
        User user2 = new User(1L, "Test firstName", "Test lastName", "test gender", 20, "test@gmail.com", "314xxxxxxx");
        User user3 = new User(3L, "Jane", "Smith", "Female", 30, "jane@example.com", "999-999-9999");

        Assertions.assertEquals(user1, user2);
        Assertions.assertNotEquals(user1, user3);
        Assertions.assertEquals(user1.hashCode(), user2.hashCode());
        Assertions.assertNotEquals(user1.hashCode(), user3.hashCode());
    }


}