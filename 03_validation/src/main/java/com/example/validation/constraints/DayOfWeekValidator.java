package com.example.validation.constraints;

import com.example.validation.constraints.annotations.DayOfWeek;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class DayOfWeekValidator
        implements ConstraintValidator<DayOfWeek, String> {
    private final Set<String> days;

    public DayOfWeekValidator(Set<String> days) {
        this.days = days;
        days.add("mon");
        days.add("tue");
        days.add("wed");
        days.add("thu");
        days.add("fri");
        days.add("sat");
        days.add("sun");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return days.contains(value);
    }
}
