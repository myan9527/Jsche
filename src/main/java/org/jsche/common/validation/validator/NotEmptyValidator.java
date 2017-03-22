package org.jsche.common.validation.validator;

import javax.validation.ConstraintValidatorContext;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.validation.constraints.NotEmpty;

@BeanValidation
public class NotEmptyValidator implements Validator<NotEmpty, String> {

    @Override
    public void initialize(NotEmpty param) {
        
    }

    @Override
    public boolean isValid(String param, ConstraintValidatorContext ctx) {
        if (param == null) return false;
        else if(param.length()<1) return false;
        return true;
    }

}
