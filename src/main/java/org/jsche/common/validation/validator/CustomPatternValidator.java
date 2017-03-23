package org.jsche.common.validation.validator;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.exception.ValidationException;
import org.jsche.common.validation.constraints.CustomPattern;

@BeanValidation
public class CustomPatternValidator extends AbstractValidator<CustomPattern, String> {

    @Override
    public void validate(CustomPattern annotation, String value) throws ValidationException {
        String regex = annotation.regex();
        if(value == null || !value.matches(regex))
            throw new ValidationException(annotation.message());
    }

}
