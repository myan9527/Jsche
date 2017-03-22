package org.jsche.common.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.jsche.common.exception.ValidationException;

public class ValidationHandler<T extends Object>{
    
    public void validate(T obj) throws ValidationException{
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(obj);
        for (ConstraintViolation<T> violation : set) {
            if(violation.getInvalidValue() != null){
                throw new ValidationException(violation.getMessage());
            }
        }
    }
}
