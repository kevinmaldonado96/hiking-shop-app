package org.example.hickingshop.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.hickingshop.entities.User;
import org.example.hickingshop.repository.UserRepository;
import org.example.hickingshop.validators.anotations.UniqueUser;

import java.util.Optional;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

    private UserRepository userRepository;

    UniqueUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username == null || username.isEmpty()) return true;
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.isEmpty();
    }
}
