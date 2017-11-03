package org.jsche.common.validation.checker;

import org.jsche.common.exception.ValidateException;
import org.jsche.common.validation.ValidationContext;
import org.jsche.common.validation.ValidationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractChecker {
    public abstract void validate(ValidationContext context) throws ValidateException;

    protected ValidationHandler handler;

    AbstractChecker(ValidationHandler handler) {
        this.handler = handler;
    }

    protected Annotation[] getValidationList(Annotation annotation) {
        Annotation[] annotations = null;
        if (annotation == null)
            return null;
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
