package org.jsche.common.validation;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.annotation.JscheConstraint;
import org.jsche.common.validation.validator.Validator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class ValidationHandler implements ApplicationContextAware {
    private Map<Annotation, Validator> annotationRules = new ConcurrentHashMap();
    private Map<String, Object> validators = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //load all validators
        validators = applicationContext.getBeansWithAnnotation(BeanValidation.class);
    }

    public Validator find(Annotation annotation) {
        Validator validator;
        if (!annotationRules.containsKey(annotation)) {
            for (Entry<String, Object> entry : validators.entrySet()) {
                if (entry != null) {
                    //match the validator
                    if (annotation.annotationType().isAnnotationPresent(JscheConstraint.class)) {
                        Class constraint = annotation.annotationType().getAnnotationsByType(JscheConstraint.class)[0].validatedBy();
                        if (constraint.isAssignableFrom((entry.getValue()).getClass()))
                            annotationRules.put(annotation, (Validator) entry.getValue());
                    }
                }
            }
        }
        validator = annotationRules.get(annotation);
        return validator;
    }
}
