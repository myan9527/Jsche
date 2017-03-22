package org.jsche.common.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import javax.validation.Constraint;
import javax.validation.Payload;

import org.jsche.common.validation.validator.NotEmptyValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NotEmptyValidator.class})
@Repeatable(org.jsche.common.validation.constraints.NotEmpty.List.class)
public @interface NotEmpty {
    String message() default "This field can not be empty.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
    
    @Documented
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
    @Retention(RUNTIME)
    public  @interface List {
        NotEmpty[] value();
    }
}
