package com.example.validation.constraints;

import com.example.validation.constraints.annotations.EmailBlackList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EmailBlackListValidator
        implements ConstraintValidator<EmailBlackList, String> {
    private Set<String> blackList;

    @Override
    public void initialize(EmailBlackList annotation) {
        // Annotation 선언시 추가했던 데이터를 받아올 수 있다.
        blackList = new HashSet<>();
        blackList.addAll(Arrays.asList(annotation.blackList()));
    }

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        // null이면 안된다.
        if (value == null) return false;
        // 이메일 주소가 아니다.
        if (!value.contains("@")) return false;
        String[] splitValue = value.split("@");
        if (splitValue.length != 2) return false;
        // 도메인을 분리한 후
        String domain = splitValue[1];
        // blackList에 담겨있는지 체크한다.
        return !blackList.contains(domain);
    }
}
