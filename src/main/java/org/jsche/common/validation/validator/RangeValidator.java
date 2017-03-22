package org.jsche.common.validation.validator;

import java.util.Collection;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jsche.common.validation.constraints.Range;

public class RangeValidator implements ConstraintValidator<Range, Object> {
    private Range range;

    @Override
    public void initialize(Range constraintAnnotation) {
        this.range = constraintAnnotation;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        long min = range.min();
        long max = range.max();

        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            return collection.size() >= min && collection.size() <= max;
        } else if (value instanceof Map) {
            Map map = (Map) value;
            return map.size() >= min && map.size() <= max;
        } else if (value.getClass().isArray()) {
            if (value instanceof int[]) {
                int[] values = (int[]) value;
                return values.length >= min && values.length <= max;
            } else if (value instanceof String[]) {
                String[] values = (String[]) value;
                return values.length >= min && values.length <= max;
            }
        } else if (value instanceof String) {
            String string = (String) value;
            return string.length() >= min && string.length() <= max;
        } else if (value instanceof Integer) {
            int intValue = (int) value;
            return intValue >= min && intValue <= max;
        } else if (value instanceof Long) {
            long longValue = (long) value;
            return longValue >= min && longValue <= max;
        } else {
            return true;
        }
        return false;
    }

}
