package org.jsche.common.validation.validator;

import org.jsche.common.exception.ValidateException;

import java.lang.annotation.Annotation;

public abstract class AbstractValidator<A extends Annotation, T> implements Validator<A, T> {

    @Override
    public void isValid(A annotation, T value) throws ValidateException {
        validate(annotation, value);
    }

    public abstract void validate(A annotation, T value) throws ValidateException;
}
