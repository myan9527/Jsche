package org.jsche.common.validation.validator;

import java.util.Collection;
import java.util.Map;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.exception.ValidationException;
import org.jsche.common.validation.constraints.Range;

@BeanValidation
public class RangeValidator extends AbstractValidator<Range, Object> {

    @SuppressWarnings("rawtypes")
    @Override
    public void validate(Range annotation, Object value) throws ValidationException {
        long min = annotation.min();
        long max = annotation.max();

        if(value != null){
            if (value instanceof Collection) {
                Collection collection = (Collection) value;
                if(collection.size() < min || collection.size() > max)
                    throw new ValidationException(annotation.message());
            } else if (value instanceof Map) {
                Map map = (Map) value;
                if(map.size() < min || map.size() > max)
                    throw new ValidationException(annotation.message());
            } else if (value.getClass().isArray()) {
                if (value instanceof int[]) {
                    int[] values = (int[]) value;
                    if(values.length < min || values.length > max)
                        throw new ValidationException(annotation.message());
                } else if (value instanceof String[]) {
                    String[] values = (String[]) value;
                    if(values.length < min || values.length > max)
                        throw new ValidationException(annotation.message());
                }
            } else if (value instanceof String) {
                String string = (String) value;
                if(string.length() < min || string.length() > max)
                    throw new ValidationException(annotation.message());
            } else if (value instanceof Integer) {
                int intValue = (int) value;
                if(intValue < min || intValue > max)
                    throw new ValidationException(annotation.message());
            } else if (value instanceof Long) {
                long longValue = (long) value;
                if(longValue < min || longValue > max)
                    throw new ValidationException(annotation.message());
            } 
            
        }

    }

}
