package org.example.hickingshop.validators.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.hickingshop.validators.UniqueEmailValidator;
import org.example.hickingshop.validators.UniqueUserValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {

    String message() default "User already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
