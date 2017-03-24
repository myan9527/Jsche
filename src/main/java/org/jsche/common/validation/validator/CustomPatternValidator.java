package org.jsche.common.validation.validator;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.exception.ValidateException;
import org.jsche.common.validation.constraints.CustomPattern;
import org.springframework.stereotype.Component;

@BeanValidation
@Component
public class CustomPatternValidator extends AbstractValidator<CustomPattern, String> {

    @Override
    public void validate(CustomPattern annotation, String value) throws ValidateException {
        String regex = annotation.regex();
        if(value == null || !value.matches(regex))
            throw new ValidateException(annotation.message());
    }

}
