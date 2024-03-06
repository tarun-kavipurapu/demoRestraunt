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
@Data
public class User {



@NotNull

    private String fullname;

@NotNull

@Indexed(unique = true)
    private String username;
@NotNull
    private String password;
    private Role role;


}
