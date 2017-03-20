package org.jsche.common.inteceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsche.common.util.AppUtil;
import org.jsche.entity.SystemUsage;
import org.jsche.repo.SystemUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SystemUsageLogInteceptor implements HandlerInterceptor {

    private SystemUsage usage;

    @Autowired
    private SystemUsageRepository sup;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
        usage = new SystemUsage();
        usage.setDateStamp(new Date(System.currentTimeMillis()));
        usage.setClientIp(AppUtil.getClienIp(req));
        usage.setMethod(req.getMethod());
        usage.setPath(req.getRequestURI());
        usage.setStatus(resp.getStatus());
        sup.save(usage);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
    }

}
