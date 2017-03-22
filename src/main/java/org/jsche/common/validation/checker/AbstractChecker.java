package org.jsche.common.validation.checker;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.validation.ConstraintValidatorContext;

public abstract class AbstractChecker {
    public abstract void validate(ConstraintValidatorContext context) ;
    
    protected Annotation[] getValidationList(Annotation annotation) {
        Annotation[] annotations = null;
        if(annotation == null)
            return annotations;
        Class<?> clazz = annotation.getClass();
        Method method;
        try {
            method = clazz.getMethod("value");
            annotations = (Annotation[]) method.invoke(annotation);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return annotations;
    }
}
