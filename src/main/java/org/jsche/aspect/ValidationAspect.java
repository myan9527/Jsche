package org.jsche.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jsche.common.exception.ControllerException;
import org.jsche.common.exception.ValidateException;
import org.jsche.common.validation.ValidationContext;
import org.jsche.common.validation.ValidationHandler;
import org.jsche.common.validation.checker.AbstractChecker;
import org.jsche.common.validation.checker.EntityChecker;
import org.jsche.common.validation.checker.MethodChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ValidationHandler handler;
    
    @Pointcut("execution(public * *(..)) && @within(org.springframework.stereotype.Controller)")
    public void validate(){
    }
    
    @Before("validate()")
    public void access(JoinPoint point){
        logger.info("**Validation log**");
        try {
            validation(point);
        } catch (ValidateException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    
    @Pointcut("execution(public * *(..)) && @within(org.springframework.web.bind.annotation.RestController)")
    public void restValidate(){
        
    }
    
    @Before("restValidate()")
    public void accessRest(JoinPoint point){
        validation(point);
    }

    private void validation(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        
        //build validation context
        ValidationContext context = new ValidationContext();
        context.setMethod(method);
        context.setArgNames(signature.getParameterNames());
        Object[] args = point.getArgs();
        context.setArgValues(args);
        context.setParameters(method.getParameters());
        logger.info("**Validation args:"+ args);
        
        //do really validation here.
        List<AbstractChecker> checkers = new ArrayList<>();
        checkers.add(new EntityChecker(handler));
        checkers.add(new MethodChecker(handler));
        
        for (AbstractChecker checker : checkers) {
            checker.validate(context);
        }
    }
}
