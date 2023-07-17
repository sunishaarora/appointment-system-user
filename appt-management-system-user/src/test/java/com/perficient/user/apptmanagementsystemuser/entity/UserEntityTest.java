package com.perficient.user.apptmanagementsystemuser.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {
    @Test
    public void testConstructorAndGetters() {
        Long userId = 1L;
        String firstName = "Test firstName";
        String lastName = "Test lastName";
        String emailAddresses = "test@gmail.com";
        String phoneNumbers = "314xxxxxxx";
        String gender = "test gender";
        int age = 20;

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmailAddresses(emailAddresses);
        userEntity.setPhoneNumbers(phoneNumbers);
        userEntity.setGender(gender);
        userEntity.setAge(age);

        Assertions.assertEquals(userId, userEntity.getUserId());
        Assertions.assertEquals(firstName, userEntity.getFirstName());
        Assertions.assertEquals(lastName, userEntity.getLastName());
        Assertions.assertEquals(emailAddresses, userEntity.getEmailAddresses());
        Assertions.assertEquals(phoneNumbers, userEntity.getPhoneNumbers());
        Assertions.assertEquals(gender, userEntity.getGender());
        Assertions.assertEquals(age, userEntity.getAge());
    }
    @Test
    public void testEqualsAndHashCode() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserId(1L);
        userEntity1.setUserId(2L);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId(1L);
        userEntity2.setUserId(2L);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setUserId(2L);
        userEntity3.setUserId(3L);

        Assertions.assertEquals(userEntity1, userEntity2);
        Assertions.assertNotEquals(userEntity1, userEntity3);
        Assertions.assertEquals(userEntity1.hashCode(), userEntity2.hashCode());
        Assertions.assertNotEquals(userEntity1.hashCode(), userEntity3.hashCode());
    }

    @Test
    public void testToString() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setFirstName("test firstName");
        userEntity.setLastName("test lastName");
        userEntity.setEmailAddresses("aashi@gmail.com");
        userEntity.setPhoneNumbers("314xxxxxxx");
        userEntity.setGender("test gender");
        userEntity.setAge(20);

        String expectedString = "UserEntity{userId=1, " +
                "firstName='test firstName', " +
                "lastName='test lastName', " +
                "gender='test gender', " +
                "age=20, " +
                "emailAddresses='aashi@gmail.com', " +
                "phoneNumbers='314xxxxxxx'}";

        String actualString = userEntity.toString();

        Assertions.assertEquals(expectedString, actualString);
        Assertions.assertEquals(1L, userEntity.getUserId());
        Assertions.assertEquals("test firstName", userEntity.getFirstName());
        Assertions.assertEquals("test lastName", userEntity.getLastName());
        Assertions.assertEquals("aashi@gmail.com", userEntity.getEmailAddresses());
        Assertions.assertEquals("314xxxxxxx", userEntity.getPhoneNumbers());
        Assertions.assertEquals("test gender", userEntity.getGender());
        Assertions.assertEquals(20, userEntity.getAge());

    }

}