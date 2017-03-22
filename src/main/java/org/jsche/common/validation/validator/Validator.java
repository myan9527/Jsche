package org.jsche.common.validation.validator;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;

public interface Validator<A extends Annotation, T> extends ConstraintValidator<A, T>{

}
