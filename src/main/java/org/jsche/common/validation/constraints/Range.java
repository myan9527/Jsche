package org.jsche.common.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
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
import org.jsche.common.validation.validator.RangeValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Documented
@JscheConstraint(validatedBy = RangeValidator.class)
@Repeatable(Range.List.class)
public @interface Range {
    long min() default 0;
    long max() default Long.MAX_VALUE;
    ErrorMessage message() default ErrorMessage.VALIDATION_ERROR;
    
    @Documented
    @Retention(RUNTIME)
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
    public @interface List{
        Range[] value();
    }
    
}
