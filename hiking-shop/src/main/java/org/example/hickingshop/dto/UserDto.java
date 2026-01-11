package org.example.hickingshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hickingshop.dto.enums.ProductType;
import org.example.hickingshop.dto.enums.Role;
import org.example.hickingshop.validators.anotations.MatchesPassword;
import org.example.hickingshop.validators.anotations.UniqueEmail;
import org.example.hickingshop.validators.anotations.UniqueUser;

import java.io.Serializable;

@Data
@NoArgsConstructor
@MatchesPassword
public class UserDto implements Serializable {

    @NotNull(message = "The name is required")
    @Size(min = 2, max = 20, message = "he name size must be minimum 2 maximum 20 characters")
    private String name;

    @NotNull(message = "The lastName is required")
    @Size(min = 2, max = 20, message = "he lastName size must be minimum 2 maximum 20 characters")
    private String lastName;

    @Email
    @UniqueEmail
    @NotNull(message = "The email is required")
    private String email;

    @NotNull(message = "The address is required")
    @Size(min = 2, max = 50, message = "The address size must be minimum 2 maximum 50 characters")
    private String address;

    @NotNull(message = "The username is required")
    @Size(min = 2, max = 50, message = "The username size must be minimum 2 maximum 50 characters")
    @UniqueUser
    private String username;

    @NotNull(message = "The password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,15}$", message = "Password must be between 8-15 characters, contain at least 1 uppercase letter and 1 number.")
    private String password;

    @NotNull(message = "The confirmPassword is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,15}$", message = "Password must be between 8-15 characters, contain at least 1 uppercase letter and 1 number.")
    private String confirmPassword;

    @NotNull
    private Role role;

}
