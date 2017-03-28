package org.jsche.common.validation.constraints;

import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.validation.validator.CustomPatternValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@JscheConstraint(validatedBy = CustomPatternValidator.class)
@Repeatable(CustomPattern.List.class)
public @interface CustomPattern {
    ErrorMessage message() default ErrorMessage.VALIDATION_ERROR;

    String regex() default "*";


    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CustomPattern[] value();
    }
}
