package com.example.validation.constraints.annotations;

import com.example.validation.constraints.DayOfWeekValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DayOfWeekValidator.class)
public @interface DayOfWeek {
    String message() default "not valid day of week";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
