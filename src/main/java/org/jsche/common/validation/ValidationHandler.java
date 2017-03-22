package org.jsche.common.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.jsche.common.exception.ValidationException;

public class ValidationHandler{
    private ValidatorFactory factory;
    
    public void validate(Object obj) throws ValidationException{
        factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(obj);
        for (ConstraintViolation<Object> violation : set) {
            if(violation.getInvalidValue() != null){
                throw new ValidationException(violation.getMessage());
            }
        }
    }
}
