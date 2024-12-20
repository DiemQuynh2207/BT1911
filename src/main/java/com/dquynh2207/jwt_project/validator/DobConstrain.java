package com.dquynh2207.jwt_project.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DobValidator.class })
public @interface DobConstrain {
    String message() default "Invalid date of birth";

    int min(); //khai báo già trị tối thiểu là bao nhiêu

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
