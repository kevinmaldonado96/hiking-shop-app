package org.example.hickingshop.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.hickingshop.dto.UserDto;
import org.example.hickingshop.validators.anotations.MatchesPassword;

public class MatchesPasswordValidator implements ConstraintValidator<MatchesPassword, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        return (userDto.getPassword() != null && userDto.getPassword().equals(userDto.getConfirmPassword()));
    }
}
