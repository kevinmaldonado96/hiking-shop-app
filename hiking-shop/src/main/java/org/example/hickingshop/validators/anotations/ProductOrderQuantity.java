package org.example.hickingshop.validators.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.hickingshop.validators.ProductOrderValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductOrderValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductOrderQuantity {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
