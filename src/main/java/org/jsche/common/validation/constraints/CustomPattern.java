package org.jsche.common.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jsche.common.ErrorMessage;
import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.validation.validator.CustomPatternValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@JscheConstraint(validatedBy = CustomPatternValidator.class)
@Repeatable(CustomPattern.List.class)
public @interface CustomPattern {
    ErrorMessage message() default ErrorMessage.VALIDATION_ERROR;
    String regex() default "*";
    
    
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        CustomPattern[] value();
    }
}
