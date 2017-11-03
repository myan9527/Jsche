package org.jsche.common.validation.checker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.exception.ValidateException;
import org.jsche.common.validation.ValidationContext;
import org.jsche.common.validation.ValidationHandler;
import org.jsche.common.validation.validator.Validator;

public class EntityChecker extends AbstractChecker {
    public EntityChecker(ValidationHandler handler) {
        super(handler);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void validate(ValidationContext context) throws ValidateException {
        Parameter[] parameters = context.getParameters();
        Object[] args = context.getArgValues();
        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].getType();
            if (type.getPackage().getName().equalsIgnoreCase("org.jsche.entity")) {
                //only validate entity class
                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    List<Annotation> annotations = new LinkedList<>();

                    for (Annotation annotation : field.getAnnotations()) {
                        if (annotation.annotationType().isAnnotationPresent(JscheConstraint.class)) {
                            annotations.add(annotation);
                        }
                    }
                    field.setAccessible(true);
                    Object value;

                    try {
                        value = field.get(args[i]);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        throw new ValidateException(e.getMessage());
                    }

                    for (Annotation annotation : annotations) {
                        Validator validator = handler.find(annotation);
                        if (validator != null)
                            validator.isValid(annotation, value);
                    }
                }
            }
        }
    }

}
