package org.jsche.common.validation.checker;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jsche.common.validation.ValidationContext;
import org.jsche.common.validation.ValidationHandler;

public abstract class AbstractChecker {
    public abstract void validate(ValidationContext context) ;
    protected ValidationHandler handler;
    
    public AbstractChecker(ValidationHandler handler){
        this.handler = handler;
    }
    
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
