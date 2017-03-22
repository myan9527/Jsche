package org.jsche.common.validation.validator;

import javax.validation.ConstraintValidatorContext;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.validation.constraints.CustomPattern;

@BeanValidation
public class CustomPatternValidator implements Validator<CustomPattern, String> {
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
