package org.jsche.common.validation.checker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.exception.ValidateException;
import org.jsche.common.validation.ValidationContext;
import org.jsche.common.validation.ValidationHandler;
import org.jsche.common.validation.validator.Validator;

public class ParameterChecker extends AbstractChecker {

    public ParameterChecker(ValidationHandler handler) {
        super(handler);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void validate(ValidationContext context) throws ValidateException {
        Parameter[] parameters = context.getParameters();
        Object[] args = context.getArgValues();
        for (int i = 0; i < parameters.length; i++) {
            //just do the validate if used for parameter.
            Class<?> type = parameters[i].getClass();
            if (type.isInstance(String.class) ||
                    type.isInstance(int.class) ||
                    type.isInstance(Long.class) ||
                    type.isArray()) {
                Annotation[] annotations = parameters[i].getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().isAnnotationPresent(JscheConstraint.class)) {
                        Validator validator = handler.find(annotation);

                        if (validator != null)
                            validator.isValid(annotation, args[i]);
                    }
                }
            }
        }
    }

}
