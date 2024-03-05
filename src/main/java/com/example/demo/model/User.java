package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * user_id--> id set by mongodb,
 * String fullname-->not null,
 * String username-->unique-->notnulll
 * String password-->not null
 * String refreshToken
 * enum role
 */
@Document("users")
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String fullname;

@NotNull

@Indexed(unique = true)
    private String username;
@NotNull
    private String password;
@NotNull
    private String refreshToken;
    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }
    private Role role;


    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }
}
