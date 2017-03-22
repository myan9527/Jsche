package org.jsche.common.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.jsche.common.validation.validator.CustomPatternValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CustomPatternValidator.class})
@Repeatable(CustomPattern.List.class)
public @interface CustomPattern {
    String message() default "Data format is not correct.";
    String regex() default "*";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
    
    
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        CustomPattern[] value();
    }
}
