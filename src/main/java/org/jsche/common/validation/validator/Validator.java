package org.jsche.common.validation.validator;

import java.lang.annotation.Annotation;

public interface Validator<A extends Annotation, T>{
    boolean isValid(A annotation, T object);

}
