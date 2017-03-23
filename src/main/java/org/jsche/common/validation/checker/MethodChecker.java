package org.jsche.common.validation.checker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.exception.ValidateException;
import org.jsche.common.validation.ValidationContext;
import org.jsche.common.validation.ValidationHandler;
import org.jsche.common.validation.validator.Validator;

public class MethodChecker extends AbstractChecker {

    public MethodChecker(ValidationHandler handler) {
        super(handler);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void validate(ValidationContext context) throws ValidateException {
        Method method = context.getMethod();
        Parameter[] parameters = context.getParameters();
//        String[] argNames = context.getArgNames();
        Object[] args = context.getArgValues();

        //validate all parameters of this method
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation.annotationType().isAnnotationPresent(JscheConstraint.class)) {
                for (int i = 0; i < parameters.length; i++) {
                    try {
                        Validator validator = handler.find(annotation);
                        if(validator != null)
                            validator.isValid(annotation, args[i]);
                    } catch (ValidateException e) {
                        throw e;
                    }
                }
            }
        }
    }

}
