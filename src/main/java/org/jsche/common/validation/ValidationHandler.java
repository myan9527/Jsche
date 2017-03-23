package org.jsche.common.validation;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.jsche.common.annotation.BeanValidation;
import org.jsche.common.validation.validator.Validator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ValidationHandler implements ApplicationContextAware {
    private Map<Annotation, Validator> rules = new ConcurrentHashMap();
    Map<String, Object> validations = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        validations = applicationContext.getBeansWithAnnotation(BeanValidation.class);
    }

    public Validator find(Annotation annotation) {
        Validator validator = null;
        if (!rules.containsKey(annotation)) {
            for (Entry<String, Object> entry : validations.entrySet()) {
                if (entry != null) {
                    rules.put(annotation, (Validator) entry.getValue());
                }
            }
        }
        validator = rules.get(annotation);
        return validator;
    }
}
