package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * user_id--> id set by mongodb,
 * String fullname-->not null,
 * String username-->unique-->notnulll
 * String password-->not null
 * enum role
 */
@Document("users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String Id;
@NotBlank
    private String fullname;

@Field("userName")
@NotBlank
@Indexed(unique = true)

    private String username;
@NotBlank
@Field("Password")

    private String password;
@NotBlank
    private Role role;

    public User(String fullname, String username, String password,Role role) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
