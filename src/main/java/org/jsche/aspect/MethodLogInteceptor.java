package org.jsche.aspect;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jsche.common.annotation.MethodLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/4/14.
 */
public class MethodLogInteceptor implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(MethodLogInteceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        logger.info("**Method log:({}), time cost:({})", methodName, (end - start));
        return result;
    }
}

@Configuration
class MethodLogConfiguration extends AbstractPointcutAdvisor {
    private Pointcut pointcut;
    private Advice advice;

    @PostConstruct
    public void init(){
        this.pointcut = new AnnotationMatchingPointcut(null, MethodLog.class);
        this.advice = new MethodLogInteceptor();
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }
}