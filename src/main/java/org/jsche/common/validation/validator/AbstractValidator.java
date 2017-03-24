package org.jsche.common.validation.validator;

import java.lang.annotation.Annotation;

import org.jsche.common.exception.ValidateException;

public abstract class AbstractValidator<A extends Annotation,T> implements Validator<A, T> {

    @Override
    public boolean isValid(A annotation, T value) throws ValidateException{
        try {
            validate(annotation,value);
        } catch (ValidateException e) {
            throw e;
        }
        return true;
    }
    
    public abstract void validate(A annotation, T value) throws ValidateException;
}
