package org.jsche.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jsche.common.validation.constraints.CustomPattern;

public class CustomPatternValidator implements ConstraintValidator<CustomPattern, String> {
    private CustomPattern pattern;
    @Override
    public void initialize(CustomPattern constraintAnnotation) {
        this.pattern = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = pattern.regex();
        if(value != null && value.matches(regex))
            return true;
        return false;
    }

}
