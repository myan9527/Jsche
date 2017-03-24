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

import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.validation.validator.NotEmptyValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Documented
@JscheConstraint(validatedBy = NotEmptyValidator.class)
@Repeatable(org.jsche.common.validation.constraints.NotEmpty.List.class)
public @interface NotEmpty {
    String message() default "This field can not be empty.";
    
    @Documented
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
    @Retention(RUNTIME)
    public  @interface List {
        NotEmpty[] value();
    }
}
