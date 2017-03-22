package org.jsche.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    @Pointcut("execution(public * *(..)) && @within(org.springframework.stereotype.Controller)")
    public void validate(){
        
    }
    
    @Before("validate()")
    public void access(JoinPoint point){
        validation(point);
    }
    
    @Pointcut("execution(public * *(..)) && @within(org.springframework.web.bind.annotation.RestController)")
    public void restValidate(){
        
    }
    
    @Before("restValidate()")
    public void accessRest(JoinPoint point){
        validation(point);
    }

    private void validation(JoinPoint point) {
        //do really validation here.
    }
}
