package org.jsche.aspect;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jsche.common.util.AppUtil;
import org.jsche.entity.SystemUsage;
import org.jsche.repo.SystemUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class SystemAccessAspect {
    private Logger logger = Logger.getLogger(getClass().getName());
    private SystemUsage usage;

    @Autowired
    private SystemUsageRepository sup;

    @Pointcut("execution(public * org.jsche.controller..*.*(..))")
    public void logPoint(){
        
    }
    
    @Before("logPoint()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        logger.info("**System access log**");
        usage = new SystemUsage();
        usage.setDateStamp(new Date(System.currentTimeMillis()));
        usage.setClientIp(AppUtil.getClienIp(req));
        usage.setMethod(req.getMethod());
        usage.setPath(req.getRequestURI());
    }
    
    @AfterReturning(returning = "r", pointcut = "logPoint()")
    public void afterReturning(Object r){
        usage.setStatus(200);
        sup.save(usage);
        logger.info(r.toString());
    }
    
    @AfterThrowing(throwing = "e", pointcut = "logPoint()")
    public void afterThrowing(Throwable e){
        usage.setStatus(500);
        sup.save(usage);
        logger.log(Level.SEVERE, e.getMessage());
    }
    
}
