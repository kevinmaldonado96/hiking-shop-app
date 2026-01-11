package org.example.hickingshop.validators.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.hickingshop.validators.MatchesPasswordValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatchesPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchesPassword {

    String message() default "Passwords do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
