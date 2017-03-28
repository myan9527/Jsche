package org.jsche.common.validation.constraints;

import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.validation.validator.NotEmptyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
@Retention(RUNTIME)
@Documented
@JscheConstraint(validatedBy = NotEmptyValidator.class)
@Repeatable(org.jsche.common.validation.constraints.NotEmpty.List.class)
public @interface NotEmpty {
    ErrorMessage message() default ErrorMessage.VALIDATION_ERROR;

    @Documented
    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
    @Retention(RUNTIME)
    @interface List {
        NotEmpty[] value();
    }
}
