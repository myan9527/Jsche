package org.jsche.common.validation.validator;

import java.lang.annotation.Annotation;

import org.jsche.common.exception.ValidateException;

public interface Validator<A extends Annotation, T>{
    boolean isValid(A annotation, T object) throws ValidateException;

}
