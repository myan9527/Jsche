package org.jsche.common.validation.validator;

import java.util.Collection;
import java.util.Map;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.exception.ValidateException;
import org.jsche.common.validation.constraints.NotEmpty;

@BeanValidation
public class NotEmptyValidator extends AbstractValidator<NotEmpty, Object> {

    @SuppressWarnings("rawtypes")
    @Override
    public void validate(NotEmpty annotation, Object value) throws ValidateException {
        if (value != null) {
            if (value instanceof Collection) {
                Collection collection = (Collection) value;
                if (collection.size() <= 0) {
                    throw new ValidateException(annotation.message());
                }
            } else if (value instanceof Map) {
                Map map = (Map) value;
                if (map.size() <= 0) {
                    throw new ValidateException(annotation.message());
                }
            } else if (value instanceof String) {
                String stringValue = (String) value;
                if (stringValue.length() <= 0) {
                    throw new ValidateException(annotation.message());
                }
            } else if (value.getClass().isArray()) {
                if (value instanceof int[]) {
                    int[] array = (int[]) value;
                    if (array.length <= 0) {
                        throw new ValidateException(annotation.message());
                    }
                } else if (value instanceof String[]) {
                    String[] array = (String[]) value;
                    if (array.length <= 0) {
                        throw new ValidateException(annotation.message());
                    }
                }
            }

        }
    }

}
