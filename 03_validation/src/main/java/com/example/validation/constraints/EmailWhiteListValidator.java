package com.example.validation.constraints;

import com.example.validation.constraints.annotations.EmailWhiteList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class EmailWhiteListValidator
        implements ConstraintValidator<EmailWhiteList, String> {

    private final Set<String> whiteList;
    public EmailWhiteListValidator() {
        whiteList = new HashSet<>();
        whiteList.add("gmail.com");
        whiteList.add("naver.com");
        whiteList.add("cau.ac.kr");
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
        // whiteList에 담겨있는지 체크한다.
        return whiteList.contains(domain);
    }
}
