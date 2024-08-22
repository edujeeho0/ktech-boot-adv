package com.example.validation.constraints.annotations;

import com.example.validation.constraints.EmailWhiteListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 Annotation이 어디(class, method, parameter)에 붙일 수 있는지
@Target({ElementType.FIELD})
// 이 Annotation이 언제까지 유지되어야 하는지
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailWhiteListValidator.class)
public @interface EmailWhiteList {
    // annotation element
    String message() default "Email not in whitelist";
    // 필요해서 추가만 해둔다.
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
