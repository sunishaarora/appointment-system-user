package com.perficient.user.apptmanagementsystemuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emailAddresses;
    private String phoneNumbers;
}
