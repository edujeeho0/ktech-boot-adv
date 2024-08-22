package com.example.validation.constraints.annotations;

import com.example.validation.constraints.EmailBlackListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailBlackListValidator.class)
public @interface EmailBlackList {
    String message() default "Email blocked";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // 대상을 지정하기 위해 Element를 추가
    String[] blackList() default {};
}
