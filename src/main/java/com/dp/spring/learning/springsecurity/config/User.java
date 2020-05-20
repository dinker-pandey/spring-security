package com.dp.spring.learning.springsecurity.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class User {
    private String email;
    private String firstName;
    private String lastName;
    private String fullName;

    public User(String email, String firstName, String lastName, String fullName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
    }
}
